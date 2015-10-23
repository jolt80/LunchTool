package lunchtool.lunchtool.parser;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by qtomsza on 10/22/15.
 */
public class HtmlBackgroundGetter extends AsyncTask<String, String, String> {

    private Exception exception;

    HtmlBackgroundGetter() {
    }

    @Override
    protected String doInBackground(String... input)  {
        HttpURLConnection urlConnection;
        try {
            // Assumes that url is in first position
            URL url = new URL(input[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            final String result = IOUtils.toString(in, "UTF-8");
            urlConnection.disconnect();
            return result;
        }
        catch (Exception e){
            Log.w("LunchTool", "caught exception" + e.toString());
            exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(String resultIn) {
    }
}
