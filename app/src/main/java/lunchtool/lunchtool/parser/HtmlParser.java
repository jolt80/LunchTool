package lunchtool.lunchtool.parser;

import android.support.v4.view.ViewPager;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Created by qtomsza on 10/22/15.
 */
public class HtmlParser extends HtmlBackgroundGetter implements Parser {

    private long lastUpdated;
    ArrayList<Restaurant> parsedRestaurants = new ArrayList();

    private final ApplicationNotifier notifier;

    /**
     * Default constructor.
     */
    public HtmlParser(final ViewPager viewPagerIn) {
        this(new StringBuilder(), new ViewPagerNotifier(viewPagerIn));
    }

    /**
     * Default constructor.
     */
    public HtmlParser(final ApplicationNotifier notifierIn) {
        this(new StringBuilder(), notifierIn);
    }

    public HtmlParser(StringBuilder sbIn, final ApplicationNotifier notifierIn)
    {
        super();
        notifier = notifierIn;

        execute("http://www.preston.se/dagens/");
    }

    @Override
    protected void onPostExecute(String resultIn) {
        super.onPostExecute(resultIn);

        try {
            // parse the preston HTML and populate the restaurant list
            parsePrestonHtml(parsedRestaurants,resultIn);
            lastUpdated = System.currentTimeMillis();
        } catch( Exception e) {
            Log.w("LunchTool",e.toString());
        }

        Log.w("LunchTool", "parsing of HTML done.");
        notifier.notifyNewDataExists();
    }

    public static void parsePrestonHtml(List<Restaurant> restaurants, final String prestonHtml) {
        org.jsoup.nodes.Document doc = Jsoup.parse(prestonHtml);

        Elements restaurantElements = doc.getElementsByAttributeValueContaining("id", "place");

        // Loop over all restaurants
        for(final Element restaurantElement : restaurantElements)
        {
            final String name = restaurantElement.getElementsByAttribute("title").get(0).text();
            Elements mealElements = restaurantElement.select("li");

            Menu menu = new Menu();

            for(final Element mealElement : mealElements)
            {
                menu.addMeal(new Meal(mealElement.text()));
            }
            Restaurant restaurant = new Restaurant(name,menu);
            restaurants.add(restaurant);
        }
    }

    @Override
    public void checkIfUpdateNeeded() {
//        if(getStatus() == Status.FINISHED) {
//            long now = System.currentTimeMillis();
//            long diff = now - lastUpdated;
//
//            if (diff > 10000) {
//                parsedRestaurants.clear();
//                execute("http://www.preston.se/dagens/");
//            }
//        }
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return parsedRestaurants;
    }
}
