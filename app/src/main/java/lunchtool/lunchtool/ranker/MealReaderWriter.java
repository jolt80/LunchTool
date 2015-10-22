package lunchtool.lunchtool.ranker;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MealReaderWriter {
    private static final String FILENAME = "allthemeals";
    private Context context;

    public MealReaderWriter(Context contextIn) {
        context = contextIn;
    }

    public void writeMeals(List<SavedMeal> meals) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject((Serializable)meals);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SavedMeal> readMeals() {
        ArrayList<SavedMeal> meals = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            meals = (ArrayList<SavedMeal>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meals;
    }
}
