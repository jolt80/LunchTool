package lunchtool.lunchtool.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;


/**
 * Created by qtomsza on 10/22/15.
 */
public class PrestonHtmlFactory {

    public static void parsePrestonHtml(List<Restaurant> restaurants, final String prestonHtml) {
        System.out.println("enter parsePrestonHtml");

        Document doc = Jsoup.parse(prestonHtml);

        Elements restaurantElements = doc.getElementsByAttributeValueContaining("id", "place");

        // Loop over all restaurants
        for(final Element restaurantElement : restaurantElements)
        {
            final String name = restaurantElement.getElementsByAttribute("title").get(0).text();
            Elements mealElements = restaurantElement.select("li");

            System.out.println(name);

            Menu menu = new Menu();

            for(final Element mealElement : mealElements)
            {
                menu.addMeal(new Meal(mealElement.text()));
            }
            Restaurant restaurant = new Restaurant(name,menu);
            restaurants.add(restaurant);
        }
    }

}
