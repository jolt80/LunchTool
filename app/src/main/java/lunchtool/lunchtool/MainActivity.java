package lunchtool.lunchtool;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import lunchtool.lunchtool.parser.HtmlParser;
import lunchtool.lunchtool.parser.Parser;
import lunchtool.lunchtool.parser.Restaurant;
import lunchtool.lunchtool.ranker.MealReaderWriter;
import lunchtool.lunchtool.ranker.SavedMealUpdater;

public class MainActivity extends AppCompatActivity {

    static private final int HUGE_DIVISABLE_NUMBER = 2*3*4*5*6*7*8*9;

    static public Parser parser;

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

        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(HUGE_DIVISABLE_NUMBER, false);

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

    @Override
    protected void onResume() {
        super.onResume();
        parser.checkIfUpdateNeeded();
        mViewPager.getAdapter().notifyDataSetChanged();
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
            return RestaurantFragment.newInstance(getVirtualPosition(position));
        }

        @Override
        public int getItemPosition(Object object){
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return parser.getRestaurants().get(getVirtualPosition(position)).getName();
        }

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return super.instantiateItem(container, getVirtualPosition(position));
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, getVirtualPosition(position), object);
        }

        public int getRestaurantCount() {
            return parser.getRestaurants().size() + 1;
        }

        public int getVirtualPosition(int position) {
            return position % getRestaurantCount();
        }

    }

}