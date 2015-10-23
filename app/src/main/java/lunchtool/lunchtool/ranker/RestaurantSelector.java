package lunchtool.lunchtool.ranker;

import java.util.List;
import java.util.Random;

import lunchtool.lunchtool.parser.Restaurant;

public class RestaurantSelector {
    private List<Restaurant> restaurants;

    public RestaurantSelector(List<Restaurant> restaurantsIn) {
        restaurants = restaurantsIn;
    }

    public Restaurant select() {
        Random rand = new Random();
        return restaurants.get(rand.nextInt(restaurants.size()));
    }
}
