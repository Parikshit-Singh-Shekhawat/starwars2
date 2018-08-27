package app.starwars.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import app.starwars.R;

/**
 * Created by apple on 25/08/18.
 */

public class BaseActivity extends AppCompatActivity {
    Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
    }

    // function to show the progress dialog
    private ProgressDialog pd;

    /**
     * function to display the progress dialog
     */
    protected void showProgressDialog() {
        hideProgressDialog();
        pd = new ProgressDialog(this, R.style.progressDialogTheme);
        pd.setCancelable(false);
        pd.show();
        View v = LayoutInflater.from(this).inflate(R.layout.progress_view, new LinearLayout(this), false);
        pd.setContentView(v);
    }

    /**
     * function to hide the progress dialog
     */
    protected void hideProgressDialog() {
        if (pd != null && pd.isShowing()) {
            pd.dismiss();
        }
    }
}
