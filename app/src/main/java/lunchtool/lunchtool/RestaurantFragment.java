package lunchtool.lunchtool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lunchtool.lunchtool.parser.Restaurant;

/**
 * A fragment containing a view of a Restaurant object.
 */
public class RestaurantFragment extends Fragment {

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
        int index = getArguments().getInt(ARG_RESTAURANT_ID);
        View rootView = getCorrectView(index, inflater, container);

        if(index >= MainActivity.parser.getRestaurants().size()) {
            return rootView;
        }

        final Restaurant restaurant = MainActivity.parser.getRestaurants().get(index);
        setTextContent(rootView, restaurant);
        return rootView;
    }

    private View getCorrectView(int index, LayoutInflater inflater, ViewGroup container) {
        if (index == 0) {
            return inflater.inflate(R.layout.fragment_suggestion, container, false);
        } else {
            return inflater.inflate(R.layout.fragment_main, container, false);
        }
    }

    private void setTextContent(View rootView, Restaurant restaurant) {
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(restaurant.getName());

        TextView menuList = (TextView) rootView.findViewById(R.id.menu_list);
        menuList.setText(restaurant.getMenu().toString());
    }
}
