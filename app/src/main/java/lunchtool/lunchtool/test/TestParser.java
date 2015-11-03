package lunchtool.lunchtool.test;

import java.util.ArrayList;
import java.util.List;

import lunchtool.lunchtool.parser.Meal;
import lunchtool.lunchtool.parser.Menu;
import lunchtool.lunchtool.parser.Parser;
import lunchtool.lunchtool.parser.Restaurant;

/**
 * Created by qtomsza on 10/22/15.
 */
public class TestParser implements Parser {

    ArrayList<Restaurant> testRestaurants = new ArrayList();

    /**
     * Default constructor.
     */
    public TestParser()
    {
        // Husman
        Menu husmanMenu = new Menu();
        husmanMenu.addMeal(new Meal("Flygande jakob"));
        husmanMenu.addMeal(new Meal("Kött"));
        husmanMenu.addMeal(new Meal("Fisk"));
        husmanMenu.addMeal(new Meal("Fågel"));
        Restaurant husman = new Restaurant("Husman",husmanMenu);

        // Chili
        Menu chiliMenu = new Menu();
        chiliMenu.addMeal(new Meal("Flygande jakob"));
        chiliMenu.addMeal(new Meal("Kött"));
        chiliMenu.addMeal(new Meal("Fisk"));
        chiliMenu.addMeal(new Meal("Fågel"));
        Restaurant chili = new Restaurant("Chili",chiliMenu);

        // ComInn
        Menu cominnMenu = new Menu();
        cominnMenu.addMeal(new Meal("Flygande jakob"));
        cominnMenu.addMeal(new Meal("Kött"));
        cominnMenu.addMeal(new Meal("Fisk"));
        cominnMenu.addMeal(new Meal("Fågel"));
        Restaurant cominn = new Restaurant("ComInn",cominnMenu);

        testRestaurants.add(husman);
        testRestaurants.add(chili);
        testRestaurants.add(cominn);
    }

    @Override
    public List<Restaurant> getRestaurants() {
        return testRestaurants;
    }

    @Override
    public void checkIfUpdateNeeded() {
    }

    @Override
    public void parse(String page) {

    }
}
