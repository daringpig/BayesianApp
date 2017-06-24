package hu.pe.lirfu.bayesianapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import hu.pe.lirfu.bayesianapp.fragments.DataFragment;
import hu.pe.lirfu.bayesianapp.fragments.MainFragment;
import hu.pe.lirfu.bayesianapp.fragments.SettingsFragment;
import hu.pe.lirfu.bayesianapp.fragments.StatisticsFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;

    static int navItemIndex = 0;

    private String[] fragmentTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        fragmentTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        if (savedInstanceState == null) {
            navItemIndex = 0;
            loadHomeFragment(new MainFragment());
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_home) {
            navItemIndex = 0;
            fragment = new MainFragment();
        } else if (id == R.id.nav_statistics) {
            navItemIndex = 1;
            fragment = new StatisticsFragment();
        } else if (id == R.id.nav_data) {
            navItemIndex = 2;
            fragment = new DataFragment();
        } else if (id == R.id.nav_settings) {
            navItemIndex = 3;
            fragment = new SettingsFragment();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        loadHomeFragment(fragment);

        return true;
    }

    private void loadHomeFragment(Fragment fragment) {
        if (fragment == null) {
            Globals.loge("Attempted loading a null pointer instead of fragment!");
            return;
        }

        getSupportActionBar().setTitle(fragmentTitles[navItemIndex]);
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.content_main, fragment, fragment.getTag());
        fragmentTransaction.commitAllowingStateLoss();

        drawer.closeDrawers();
        invalidateOptionsMenu();
    }
}
