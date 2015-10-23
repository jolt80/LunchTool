package lunchtool.lunchtool;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lunchtool.lunchtool.parser.Restaurant;
import lunchtool.lunchtool.ranker.RestaurantSelector;

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
        if(MainActivity.parser.getRestaurants().size() == 0) {
            return inflater.inflate(R.layout.fragment_loading, container, false);
        }
        int index = getArguments().getInt(ARG_RESTAURANT_ID);
        return getCorrectView(index, inflater, container);
    }

    private View getCorrectView(int index, LayoutInflater inflater, ViewGroup container) {
        if (index == 0) {
            return getSuggestionView(inflater, container);
        } else {
            return getNormalRestaurant(index, inflater, container);
        }
    }

    private View getSuggestionView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_suggestion, container, false);
        RestaurantSelector selector = new RestaurantSelector(MainActivity.parser.getRestaurants());
        Restaurant restaurant = selector.select();
        setTextContent(rootView, restaurant);
        return rootView;
    }

    private View getNormalRestaurant(int index, LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Restaurant restaurant = MainActivity.parser.getRestaurants().get(index - 1);
        setTextContent(rootView, restaurant);
        return rootView;
    }

    private void setTextContent(View rootView, Restaurant restaurant) {
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(restaurant.getName());

        TextView menuList = (TextView) rootView.findViewById(R.id.menu_list);
        menuList.setText(restaurant.getMenu().toString());
    }
}
