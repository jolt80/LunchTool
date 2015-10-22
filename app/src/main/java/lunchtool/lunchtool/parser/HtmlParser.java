package lunchtool.lunchtool.parser;

import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qtomsza on 10/22/15.
 */
public class HtmlParser extends HtmlBackgroundGetter implements Parser {

    ArrayList<Restaurant> parsedRestaurants = new ArrayList();

    private StringBuilder sb;
    private final ViewPager pager;

    /**
     * Default constructor.
     */
    public HtmlParser(final ViewPager pagerIn) {
        this(new StringBuilder(), pagerIn);
    }

    public HtmlParser(StringBuilder sbIn, final ViewPager pagerIn)
    {
        super(sbIn);
        sb = sbIn;
        pager = pagerIn;

        execute("http://www.datum.nu/");
    }

    @Override
    protected void onPostExecute(String resultIn) {
        super.onPostExecute(resultIn);

        final String result = sb.toString();

        // Test
        Menu testMenu = new Menu();
        testMenu.addMeal(new Meal(result));
        Restaurant test = new Restaurant("Datum.nu",testMenu);
        parsedRestaurants.add(test);

        pager.getAdapter().notifyDataSetChanged();
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return parsedRestaurants;
    }
}
