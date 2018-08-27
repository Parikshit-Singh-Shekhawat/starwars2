package app.starwars.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by apple on 25/08/18.
 */

public class Utilities {

    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connec = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);


        NetworkInfo activeNetwork = connec.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();


        if (isConnected)
            return true;

        return false;
    }


    public static String convertDateFormats(String dateString, String format1, String format2) {
        String dt=null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat(format1);
        dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date value = null;
        try {
            value = dateFormatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return dt;

        }

        SimpleDateFormat formatter = new SimpleDateFormat(format2);
        formatter.setTimeZone(TimeZone.getDefault());
        dt = formatter.format(value);
        return dt;
    }

    public static  double cmToMeters(int cm)
    {
        final double KILOMETER  = 0.01;
        return cm * KILOMETER;
    }
}
