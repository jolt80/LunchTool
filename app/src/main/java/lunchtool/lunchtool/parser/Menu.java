package lunchtool.lunchtool.parser;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qtomsza on 10/22/15.
 */
public class Menu {
    private HashSet<Meal> meals = new HashSet<>();

    public Set<Meal> getMeals()
    {
        return meals;
    }

    void addMeal(final Meal meal)
    {
        meals.add(meal);
    }
}
