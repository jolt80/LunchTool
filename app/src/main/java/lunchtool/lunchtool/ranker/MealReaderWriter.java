package lunchtool.lunchtool.ranker;

import android.content.Context;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MealReaderWriter {
    private static final String FILENAME = "allthemeals";
    private Context context;

    public MealReaderWriter(Context contextIn) {
        context = contextIn;
    }

    public void writeMeals(Map<String, Integer> meals) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);
            outputStream.writeObject((Serializable)meals);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Integer> readMeals() {
        HashMap<String, Integer> meals = new HashMap<>();
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            ObjectInputStream inputStream = new ObjectInputStream(fis);
            meals = (HashMap<String, Integer>)inputStream.readObject();
        } catch (Exception e) {
            Log.w("lunchtool", "No meals found, returning empty map");
            meals = new HashMap<String, Integer>();
        }
        return meals;
    }
}
