package lunchtool.lunchtool.ranker;

import java.util.Map;

import lunchtool.lunchtool.parser.Meal;
import lunchtool.lunchtool.parser.Restaurant;

public class SavedMealUpdater {
    MealReaderWriter readerWriter;
    Restaurant selectedRestaurant;

    public SavedMealUpdater(Restaurant restaurantIn, MealReaderWriter readerWriterIn)
    {
        selectedRestaurant = restaurantIn;
        readerWriter = readerWriterIn;
    }

    public void update() {
        Map<String, Integer> meals = readerWriter.readMeals();
        incrementOrAddKeys(meals);
        readerWriter.writeMeals(meals);
    }

    private void incrementOrAddKeys(Map<String, Integer> savedMeals) {
        for (Meal meal : selectedRestaurant.getMenu().getMeals()) {
            KeyMeal key = new KeyMeal(meal, selectedRestaurant.getName());
            incrementOrAddKey(key, savedMeals);
        }
    }

    private void incrementOrAddKey(KeyMeal key, Map<String, Integer> savedMeals) {
        Integer selectedCount = savedMeals.get(key.toString());
        if (selectedCount == null) {
            savedMeals.put(key.toString(), 0);
        } else {
            selectedCount += 1;
            savedMeals.put(key.toString(), selectedCount);
        }
    }
}
