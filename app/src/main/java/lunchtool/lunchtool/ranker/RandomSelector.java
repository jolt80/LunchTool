package lunchtool.lunchtool.ranker;

import java.util.List;
import java.util.Random;

import lunchtool.lunchtool.parser.Restaurant;

public class RandomSelector implements RestaurantSelector {
    private List<Restaurant> restaurants;

    public RandomSelector(List<Restaurant> restaurantsIn) {
        restaurants = restaurantsIn;
    }

    @Override
    public Restaurant select() {
        Random rand = new Random();
        return restaurants.get(rand.nextInt(restaurants.size()));
    }
}
