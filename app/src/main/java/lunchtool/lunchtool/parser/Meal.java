package lunchtool.lunchtool.parser;

import java.io.Serializable;

/**
 * Created by qtomsza on 10/22/15.
 */
public class Meal implements Comparable<Meal>, Serializable {
    private String name;

    public Meal(final String nameIn) {
        name = nameIn;
    }

    public String getName() {
        return name;
    }

    @Override
    public final String toString() {
        return getName();
    }

    @Override
    public int compareTo(Meal another) {
        return name.compareTo(another.name);
    }
}