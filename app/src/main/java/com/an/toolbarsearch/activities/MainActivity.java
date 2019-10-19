package com.an.toolbarsearch.activities;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;

import com.an.toolbarsearch.R;
import com.an.toolbarsearch.common.BaseActivity;
import com.an.toolbarsearch.config.AppConst;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    // Constants
    private static final String TAG = MainActivity.class.getSimpleName();

    // UI Components
    private Menu mMenu;

    private List<String> mResultStringList;
    private boolean mBackPressedToExitOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar(getResources().getString(R.string.main_activity_title), MainActivity.this);
        initView();
    }

    private void initView() {
        mResultStringList = generateStringList();
    }

    private List<String> generateStringList() {
        List<String> stringList = new ArrayList<>();

        String text1 = "ABC";
        String text2 = "CBA";
        String text3 = "PQR";
        String text4 = "RQP";
        String text5 = "XYZ";
        String text6 = "ZYX";
        String text7 = "A";
        String text8 = "P";
        String text9 = "X";

        stringList.add(text1);
        stringList.add(text2);
        stringList.add(text3);
        stringList.add(text4);
        stringList.add(text5);
        stringList.add(text6);
        stringList.add(text7);
        stringList.add(text8);
        stringList.add(text9);

        return stringList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main_search, menu);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {

        if (menuItem.getItemId() == R.id.menu_activity_main_search_item_search) {

            final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
            searchView.setOnQueryTextListener(this);

            MenuItemCompat.setOnActionExpandListener(menuItem,
                    new MenuItemCompat.OnActionExpandListener() {
                        @Override
                        public boolean onMenuItemActionCollapse(MenuItem item) {
                            // Do something when collapsed
                            /*if (mAdapter != null) {
                                mAdapter.setFilter(mSvoList);
                            }*/
                            return true; // Return true to collapse action view
                        }

                        @Override
                        public boolean onMenuItemActionExpand(MenuItem item) {
                            // Do something when expanded
                            return true; // Return true to expand action view
                        }
                    });
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mBackPressedToExitOnce) {
            super.onBackPressed();

        } else {
            this.mBackPressedToExitOnce = true;
            Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mBackPressedToExitOnce = false;
                }
            }, AppConst.BACK_PRESSED_DELAY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        for (final String stringText : mResultStringList) {
            if (stringText.toLowerCase().equals(query.toLowerCase())) {
                Log.d(TAG, "Search Submitted: " + stringText);
                MenuItem searchMenuItem = mMenu.findItem(R.id.menu_activity_main_search_item_search);
                if (searchMenuItem.isActionViewExpanded()) {
                    searchMenuItem.collapseActionView();
                }
            }
        }

        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<String> filteredStringList = filter(mResultStringList, newText);

        if (filteredStringList != null && filteredStringList.size() > 0) {
            /*mRecyclerView.setVisibility(View.VISIBLE);
            mLlNoContentFoundElementContainer.setVisibility(View.GONE);
            mAdapter.setFilter(filteredStringList);*/
            for (String filteredStringItem : filteredStringList) {
                Log.d(TAG, filteredStringItem);
            }
        } else {
            Log.d(TAG, "No Results");
            /*mRecyclerView.setVisibility(View.GONE);
            mLlNoContentFoundElementContainer.setVisibility(View.VISIBLE);*/
        }
        return true;
    }

    /*
     * Filter criteria will be -> (LIKE - CONTAINS)
     * */
    private List<String> filter(List<String> modelStringList, String query) {
        query = query.toLowerCase();

        final List<String> filteredModelStringList = new ArrayList<>();
        if (!query.isEmpty()) {
            for (String stringItem : modelStringList) {

                if (stringItem.toLowerCase().contains(query)) {
                    filteredModelStringList.add(stringItem);
                }

            }
        }
        return filteredModelStringList;
    }
}
