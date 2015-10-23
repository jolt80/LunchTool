package lunchtool.lunchtool.ranker;

        import java.io.Serializable;

        import lunchtool.lunchtool.parser.Meal;

public class KeyMeal implements Serializable{
    private Meal meal;
    private String restaurant;

    public KeyMeal(final Meal mealIn, final String restaurantIn) {
        meal = mealIn;
        restaurant = restaurantIn;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(restaurant);
        sb.append(' ');
        sb.append(meal.toString());
        return sb.toString();
    }
}