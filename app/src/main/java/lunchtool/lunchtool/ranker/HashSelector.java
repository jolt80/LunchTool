package lunchtool.lunchtool.ranker;

import android.util.Log;

import java.util.List;

import lunchtool.lunchtool.parser.Restaurant;

public class HashSelector implements RestaurantSelector{
    Restaurant selectedRestaurant;

    public HashSelector(List<Restaurant> restaurants) {
        int index = generateIndexFromList(restaurants);
        selectedRestaurant = restaurants.get(index);
    }

    int generateIndexFromList(List<Restaurant> restaurants) {
        String restaurantString = restaurants.toString();
        int hash = restaurantString.hashCode();
        return Math.abs(hash) % restaurants.size();
    }

    @Override
    public Restaurant select() {
        return selectedRestaurant;
    }
}
