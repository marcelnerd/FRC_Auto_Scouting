package com.example.cameron.sql_testing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.sqlite.*;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends FragmentActivity {
    public static Context c; // Static context that can be accessed from other classes

    public DatabaseContainer container = new DatabaseContainer(this);
    public TextView textBox;
    Button button;
    TBAHandler handler;
    BottomNavigationView bottomNav;
    ListView list;
    private static int currentMatch = 1;
    private int mSelectedItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getBaseContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //textBox = findViewById(R.id.textBox);
        button = findViewById(R.id.button);
        //bottomNav = findViewById(R.id.bottomNav);
        list = findViewById(R.id.listMain);
        handler = new TBAHandler(this);

        //handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018_mndu", 1));
        //Log.v("minto", handler.helper.getAllEntries());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", currentMatch));
            }
        });

         /*bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               // setFragment(item.getItemId());
                return true;
            }
        }); */

        MenuItem selectedItem;
      /*  if (savedInstanceState != null) {
            mSelectedItem = savedInstanceState.getInt("arg_selected_item", 0);
            selectedItem = bottomNav.getMenu().findItem(mSelectedItem);
        } else {
            selectedItem = bottomNav.getMenu().getItem(0);
        }
        setFragment(selectedItem.getItemId());

        bottomNav.getMenu().getItem(0).setChecked(true); */

        TodoCursorAdapter todoAdapter = new TodoCursorAdapter(this, TBAHandler.helper.getAllEntriesCursor());
        list.setAdapter(todoAdapter);
    }

    public static void setCurrentMatch(int c) {
        currentMatch = c;
    }

    public static int getCurrentMatch() {
        return currentMatch;
    }

  /*  private void setFragment(int itemID) {

        //Fragment frag = ItemFragment.newInstance(4);

        switch (itemID) {
            case R.id.navigation_home:
                frag = DashboardFragment.newInstance("1", "2");
                break;

            case R.id.navigation_dashboard:
                frag = MapFragment.newInstance("1", "2");
                break;

            case R.id.navigation_notifications:
                frag = SettingsFragment.newInstance("1", "2");
                break;

            default:
                frag = null;
                break;
        }

        mSelectedItem = itemID;

        // uncheck the other items.
        for (int i = 0; i< bottomNav.getMenu().size(); i++) {
            MenuItem menuItem = bottomNav.getMenu().getItem(i);
            menuItem.setChecked(menuItem.getItemId() == itemID);
        }

        if (frag != null) {
            ConstraintLayout cl = (ConstraintLayout) findViewById(R.id.container);
            cl.removeAllViews();
            cl.addView(bottomNav);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.container, frag, frag.getTag());
            ft.commit();
        }

    } */



}
