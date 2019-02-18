package com.mdg.iitr.cognizance19;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;

import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    public static int REGISTRATION_TYPE_PARTICIPANT = 0;
    public static int REGISTRATION_TYPE_SPP = 1;
    public static int SCREEN_WIDTH = 0;
    public static int EVENT_FRAG = 0;
    public static NavController navController;
    public static BottomNavigationView bottomNavigationView;
    public static int DEPARTMENT_FLAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_WIDTH = displayMetrics.widthPixels;
        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
        bottomNavigationView = findViewById(R.id.bottom_nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.homeMenuFragment, false)
                        .build();

                switch (menuItem.getItemId()) {
                    case R.id.home:
                        navController.navigate(R.id.homeMenuFragment, null, navOptions);
                        return true;
                    case R.id.events:
                        Bundle bundle = new Bundle();
                        bundle.putInt("event_frag_id", EVENT_FRAG);
                        navController.navigate(R.id.centerStageAndDepartmentalFragment, bundle, navOptions);
                        return true;
                    case R.id.workshops:
                        navController.navigate(R.id.workshopFragment, null, navOptions);
                        return true;
                    case R.id.whatsNew:
                        Bundle bundle1 = new Bundle();
                        bundle1.putInt("event_frag_id", EVENT_FRAG);
                        navController.navigate(R.id.finfestAndLitfestFragment, bundle1, navOptions);
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }
}
