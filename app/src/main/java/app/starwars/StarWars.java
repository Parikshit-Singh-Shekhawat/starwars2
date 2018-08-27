package app.starwars;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatDelegate;

/**
 * Created by apple on 25/08/18.
 */

public class StarWars extends Application {

    public static String BASE_URL;
    private static StarWars mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        StarWars.context = getApplicationContext();
        BASE_URL=getResources().getString(R.string.BASE_URL);
    }
}
