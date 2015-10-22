package lunchtool.lunchtool.parser;

import android.support.v4.view.ViewPager;
import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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

    ArrayList<Restaurant> parsedRestaurants = new ArrayList();

    private StringBuilder sb;
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
        super(sbIn);
        sb = sbIn;
        notifier = notifierIn;

        execute("http://www.preston.se/dagens/");
    }

    @Override
    protected void onPostExecute(String resultIn) {
        super.onPostExecute(resultIn);

        try {
            // parse the preston HTML and populate the restaurant list
            PrestonHtmlFactory.parsePrestonHtml(parsedRestaurants,resultIn);
        } catch( Exception e) {
            Log.w("LunchTool",e.toString());
        }

        notifier.notifyNewDataExists();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return parsedRestaurants;
    }
}
