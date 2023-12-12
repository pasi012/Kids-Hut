package inc.sltechnology.kidshut;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import inc.sltechnology.kidshut.Fragments.ChineseFragment;
import inc.sltechnology.kidshut.Fragments.EnglishFragment;
import inc.sltechnology.kidshut.Fragments.HindiFragment;
import inc.sltechnology.kidshut.Fragments.JapaneseFragment;
import inc.sltechnology.kidshut.Fragments.SinhalaFragment;
import inc.sltechnology.kidshut.Fragments.TamilFragment;
import inc.sltechnology.kidshut.Nav_Activities.FAQActivity;
import inc.sltechnology.kidshut.Nav_Activities.T_C_Activity;
import inc.sltechnology.kidshut.Nav_Activities.aboutActivity;
import inc.sltechnology.kidshut.Nav_Activities.contactActivity;
import inc.sltechnology.kidshut.loginActivities.LoginActivity;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Load_Settings();

        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout = findViewById(R.id.nav_drawer);
        navigationView = findViewById(R.id.nav_view);

        toolbar = findViewById(R.id.toolbarHome);
        setSupportActionBar(toolbar);
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        initViewPager();
        FirebaseApp.initializeApp(this);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

    }

    private void Load_Settings(){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String orientation = sharedPreferences.getString("ORIENTATION", "false");

        if ("1".equals(orientation)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);

        }else if ("2".equals(orientation)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }else if ("3".equals(orientation)){

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        }

    }

    @Override
    protected void onResume() {
        Load_Settings();
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.Exit){

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Do You Want To Exit ?");
            builder.setCancelable(true);

            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    finish();

                }
            });

            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.cancel();

                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }else if (id == R.id.setting){

            Intent intent = new Intent(this, Preference.class);
            startActivity(intent);

        }else if (id == R.id.Search){

            Intent intent = new Intent(this, SearchBarActivity.class);
            startActivity(intent);

        }

        return true;
        
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit ?");
        builder.setCancelable(true);

        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();

            }
        });

        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void initViewPager() {

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new EnglishFragment(), "ENGLISH");
        viewPagerAdapter.addFragments(new TamilFragment(), "தமிழ்");
        viewPagerAdapter.addFragments(new HindiFragment(), "हिन्दी भाषा");
        viewPagerAdapter.addFragments(new SinhalaFragment(), "සිංහල");
        viewPagerAdapter.addFragments(new JapaneseFragment(), "日本語");
        viewPagerAdapter.addFragments(new ChineseFragment(), "中文");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.nav_home:
                break;

            case R.id.nav_aboutus:
                Intent intent = new Intent(MainActivity.this, aboutActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_faq:
                Intent intent1 = new Intent(MainActivity.this, FAQActivity.class);
                startActivity(intent1);
                break;

            case R.id.nav_t_c:
                Intent intent2 = new Intent(MainActivity.this, T_C_Activity.class);
                startActivity(intent2);
                break;

            case R.id.nav_logout:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are You Sure, Do You Want To Logout ?");
                builder.setCancelable(true);

                builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        finish();
                        firebaseAuth.signOut();

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);

                    }
                });

                builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;

            case R.id.nav_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_rateus:
                Toast.makeText(this, "rate us", Toast.LENGTH_SHORT).show();
                break;

            case R.id.nav_contactus:
                Intent intent3 = new Intent(MainActivity.this, contactActivity.class);
                startActivity(intent3);
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;
        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();

        }

        void addFragments(Fragment fragment, String title){

            fragments.add(fragment);
            titles.add(title);

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}