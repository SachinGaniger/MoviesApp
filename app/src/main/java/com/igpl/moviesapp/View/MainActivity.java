package com.igpl.moviesapp.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.igpl.moviesapp.R;
import com.igpl.moviesapp.Utils.CommonRecyclerviewClickListener;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements CommonRecyclerviewClickListener {

    private static final String TAG = "MainActivity";

    ImageView iv_back;

    BottomNavigationView bv_navigation;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        bv_navigation = findViewById(R.id.bv_navigation);

        navController = Navigation.findNavController(MainActivity.this, R.id.navHost);

        NavigationUI.setupWithNavController(bv_navigation, navController);

    }



    @Override
    public void OnItemClick(int position) {

//        Intent intent = new Intent(MainActivity.this, MovieDetails.class);
//        intent.putExtra("movieId", popularMovieList.get(position).getId());
//        startActivity(intent);
    }
}