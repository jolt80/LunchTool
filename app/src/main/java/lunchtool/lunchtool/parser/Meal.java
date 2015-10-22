package lunchtool.lunchtool.parser;

/**
 * Created by qtomsza on 10/22/15.
 */
public class Meal {
    private String name;

    public Meal(final String nameIn)
    {
        name = nameIn;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public final String toString() {
        return getName();
    }

}
