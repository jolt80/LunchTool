package lunchtool.lunchtool.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by qtomsza on 10/22/15.
 */
public class Menu {
    private ArrayList<Meal> meals = new ArrayList<Meal>();

    public List<Meal> getMeals()
    {
        return meals;
    }

    public void addMeal(final Meal meal)
    {
        meals.add(meal);
    }

    @Override
    public final String toString() {
        StringBuilder sb = new StringBuilder();

        for( final Meal meal : meals) {
            sb.append(meal.toString());
            sb.append("\n\n");
        }
        return sb.toString();
    }
}
