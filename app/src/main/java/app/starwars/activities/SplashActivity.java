package app.starwars.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import app.starwars.R;
import app.starwars.utilities.Utilities;

/**
 * Created by apple on 25/08/18.
 */

public class SplashActivity extends AppCompatActivity {
        Context ctx;
        final int TIME_OUT = 2000;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            ctx = this;
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.activity_splash);
            nextScreen();
        }

    private void nextScreen() {

                if (Utilities.isInternetConnected(ctx)) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent=new Intent(ctx,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }, TIME_OUT);
                }
                else {
                    Toast.makeText(ctx, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
                }
    }

}
