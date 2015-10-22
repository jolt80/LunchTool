package lunchtool.lunchtool.ranker;

import java.io.Serializable;

import lunchtool.lunchtool.parser.Meal;

public class SavedMeal implements Serializable{
    private Meal meal;
    private String restaurant;

    private int timesChosen = 0;

    public SavedMeal() {}

    public SavedMeal(final Meal mealIn, final String restaurantIn) {
        meal = mealIn;
        restaurant = restaurantIn;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(meal.toString());
        sb.append(' ');
        sb.append(restaurant);
        sb.append(' ');
        sb.append(timesChosen);
        return sb.toString();
    }

    public int getTimesChosen() {
        return timesChosen;
    }

    public void chosen() {
        timesChosen++;
    }
}
