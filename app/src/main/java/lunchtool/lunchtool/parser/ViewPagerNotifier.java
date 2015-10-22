package lunchtool.lunchtool.parser;

import android.support.v4.view.ViewPager;

/**
 * Created by qtomsza on 10/22/15.
 */
public class ViewPagerNotifier implements ApplicationNotifier {

    private final ViewPager viewPager;

    public ViewPagerNotifier(final ViewPager viewPagerIn) {
        viewPager = viewPagerIn;
    }

    @Override
    public void notifyNewDataExists() {
        viewPager.getAdapter().notifyDataSetChanged();
    }
}
