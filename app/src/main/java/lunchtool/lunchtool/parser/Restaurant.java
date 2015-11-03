package lunchtool.lunchtool.parser;

import android.nfc.NfcAdapter;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by qtomsza on 10/22/15.
 */
public class Restaurant  {
    String name;
    Menu menu;

    public Restaurant(final String nameIn, final Menu menuIn) {
        name = nameIn;
        menu = menuIn;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name + " " + menu.toString();
    }
}
