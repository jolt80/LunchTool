package lunchtool.lunchtool;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import lunchtool.lunchtool.parser.HtmlParser;
import lunchtool.lunchtool.parser.Parser;
import lunchtool.lunchtool.parser.Restaurant;
import lunchtool.lunchtool.ranker.MealReaderWriter;
import lunchtool.lunchtool.ranker.SavedMealUpdater;
import lunchtool.lunchtool.test.TestParser;

public class MainActivity extends AppCompatActivity {

    static private Parser parser;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);

        // Product code parser
        parser = new HtmlParser(mViewPager);

        // Test parser
        //parser = new TestParser();

        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restaurantSelected();
                Snackbar.make(view, "Good for you", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void restaurantSelected() {
        int currentItem = mViewPager.getCurrentItem();
        Restaurant selectedRestaurant = parser.getRestaurants().get(currentItem);
        SavedMealUpdater mealUpdater = new SavedMealUpdater(selectedRestaurant,
                                                            new MealReaderWriter(this));
        mealUpdater.update();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a RestaurantFragment (defined as a static inner class below).
            return RestaurantFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return parser.getRestaurants().size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return parser.getRestaurants().get(position).getName();
        }
    }

    /**
     * A fragment containing a view of a Restaurant object.
     */
    public static class RestaurantFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_RESTAURANT_ID = "restaurant_id";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static RestaurantFragment newInstance(int restaurantId) {
            RestaurantFragment fragment = new RestaurantFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_RESTAURANT_ID, restaurantId);
            fragment.setArguments(args);
            return fragment;
        }

        public RestaurantFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final Restaurant restaurant = MainActivity.parser.getRestaurants().get(getArguments().getInt(ARG_RESTAURANT_ID));

            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(restaurant.getName() + '\n');

            TextView menuList = (TextView) rootView.findViewById(R.id.menu_list);
            menuList.setText(restaurant.getMenu().toString());

            return rootView;
        }
    }
}