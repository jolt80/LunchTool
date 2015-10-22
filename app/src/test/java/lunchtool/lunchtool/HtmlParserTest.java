package lunchtool.lunchtool;

import android.support.v4.view.ViewPager;

import org.apache.commons.io.IOUtils;
import org.easymock.EasyMock;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import lunchtool.lunchtool.parser.ApplicationNotifier;
import lunchtool.lunchtool.parser.HtmlParser;
import lunchtool.lunchtool.parser.PrestonHtmlFactory;
import lunchtool.lunchtool.parser.Restaurant;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class HtmlParserTest {
    @Test
    public void testParsing() throws Exception {
        HttpURLConnection urlConnection;

        URL url = new URL("http://www.preston.se/dagens/");
        urlConnection = (HttpURLConnection) url.openConnection();
        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
        final String result = IOUtils.toString(in, "UTF-8");
        urlConnection.disconnect();

        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

        // Test object
        PrestonHtmlFactory.parsePrestonHtml(restaurants,result);

        for(final Restaurant restaurant : restaurants) {
            System.out.println(restaurant.getName() + '\n');
            System.out.println(restaurant.getMenu().toString());
        }
    }
}