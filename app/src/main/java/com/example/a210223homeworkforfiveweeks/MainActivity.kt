package com.example.a210223homeworkforfiveweeks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.a210223homeworkforfiveweeks.databinding.ActivityMainBinding
import com.example.a210223homeworkforfiveweeks.tabs.TabAddMovieItemBottomNav
import com.example.a210223homeworkforfiveweeks.tabs.TabMovieFilterItemBottomNav
import com.example.a210223homeworkforfiveweeks.tabs.TabDeleteGenresItemBottomNav
import com.example.a210223homeworkforfiveweeks.tabs.TabDeleteMovieItemBottomNav
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity  : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

   binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)


        supportFragmentManager.beginTransaction().replace(R.id.content, TabAddMovieItemBottomNav()).commit()

        binding?.bottomNav?.setOnNavigationItemSelectedListener(this)
        binding?.bottomNav?.selectedItemId = R.id.addMovieItemBottomNav

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.addMovieItemBottomNav -> supportFragmentManager.beginTransaction()
                .replace(R.id.content, TabAddMovieItemBottomNav()).commit()
            R.id.MovieGenresItemBottomNav -> supportFragmentManager.beginTransaction()
                .replace(R.id.content, TabDeleteGenresItemBottomNav()).commit()
            R.id.MovieItemBottomNav -> supportFragmentManager.beginTransaction()
                .replace(R.id.content, TabDeleteMovieItemBottomNav()).commit()
            R.id.MovieFilterItemBottomNav -> supportFragmentManager.beginTransaction()
                .replace(R.id.content, TabMovieFilterItemBottomNav()).commit()
        }

        return true
    }
}
