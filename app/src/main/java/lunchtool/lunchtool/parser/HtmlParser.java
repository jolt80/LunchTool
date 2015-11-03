package lunchtool.lunchtool.parser;

import android.os.AsyncTask;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtomsza on 10/22/15.
 */
public class HtmlParser implements Parser {

    private static final String PRESTON_URL = "http://www.preston.se/dagens/";

    private static final int UPDATE_TIME = 3600 * 1000;

    private long lastUpdated = 0;
    ArrayList<Restaurant> parsedRestaurants = new ArrayList();

    private final ApplicationNotifier notifier;

    private HtmlBackgroundGetter htmlBackgroundGetter;

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

            if (!(menu.isEmpty() || name.equals("Coor"))) {
                Restaurant restaurant = new Restaurant(name, menu);
                restaurants.add(restaurant);
            }
        }
    }

    @Override
    public void checkIfUpdateNeeded() {
        if(isReady()) {
            long now = System.currentTimeMillis();
            long diff = now - lastUpdated;

            if (diff > UPDATE_TIME) {
                parsedRestaurants.clear();
                htmlBackgroundGetter = new HtmlBackgroundGetter(this);
                htmlBackgroundGetter.execute(PRESTON_URL);
            }
        }

    }

    public boolean isReady() {
        return htmlBackgroundGetter == null ||
                htmlBackgroundGetter.getStatus() == AsyncTask.Status.FINISHED;
    }

    @Override
    public void parse(String page) {
        try {
            // parse the preston HTML and populate the restaurant list
            parsePrestonHtml(parsedRestaurants,page);
            lastUpdated = System.currentTimeMillis();
        } catch( Exception e) {
            Log.w("LunchTool",e.toString());
        }

        Log.w("LunchTool", "parsing of HTML done.");
        notifier.notifyNewDataExists();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return parsedRestaurants;
    }
}
