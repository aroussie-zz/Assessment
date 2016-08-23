package com.allstate.alexandreroussiere.allstate.ui;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;


import com.allstate.alexandreroussiere.allstate.R;

public class HomeActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        setToolbar();
        setDrawerNavigation();

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.drawer_icon);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(R.string.home_title);
    }

    private void setDrawerNavigation() {

        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer);
        navigationView = (NavigationView) findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                Fragment fragment = null;
                int itemId = item.getItemId();

                if ( itemId == R.id.facts ) {
                    fragment = new FactsFragment();
                    getSupportActionBar().setTitle(R.string.facts_title);
                }

                else if ( itemId == R.id.map ){
                    fragment = new MapFragment();
                    getSupportActionBar().setTitle(R.string.map_title);

                }

                else if ( itemId == R.id.accelerometer ){
                    fragment = new AccelerometerFragment();
                    getSupportActionBar().setTitle(R.string.accelerometer_title);
                }

                if ( fragment != null ){
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                    fragmentTransaction.replace(R.id.content_frame,fragment);
                    fragmentTransaction.commit();
                    drawerLayout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        switch(itemId) {

            case android.R.id.home: {

                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }

        return false;

    }
}
