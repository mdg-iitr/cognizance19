package com.mdgiitr.karthik.cognizance19;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    public static int REGISTRATION_TYPE_PARTICIPANT = 0;
    public static int REGISTRATION_TYPE_SPP = 1;
    public static int SCREEN_WIDTH = 0;
    public static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        SCREEN_WIDTH = displayMetrics.widthPixels;

        navController = Navigation.findNavController(this, R.id.my_nav_host_fragment);
    }
}
