package com.an.toolbarsearch.common;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.an.toolbarsearch.R;
import com.an.toolbarsearch.activities.MainActivity;

public class BaseActivity extends AppCompatActivity {

    // constants
    private static final String TAG = BaseActivity.class.getSimpleName();

    private Toolbar mToolbar;

    public BaseActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setToolbar(String toolbarName, Activity activity) {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        TextView tvTitle = findViewById(R.id.tv_toolbar_title);
        tvTitle.setText(toolbarName);
        if (toolbarName.isEmpty()) {
            // mToolbar.setBackgroundColor(Color.TRANSPARENT);
        }
        setSupportActionBar(mToolbar);
        if (!activity.getClass().getSimpleName().equals(MainActivity.class.getSimpleName())) {
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //set custom navigation icon
        /*getSupportActionBar().setHomeAsUpIndicator(AppUtil.getDrawable(Iconify.IconValue.arrow_left,
                ContextCompat.getColor(this, R.color.color_toolbar_icon_white), this, 34));*/
    }

}
