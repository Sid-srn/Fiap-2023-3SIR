package com.example.naviagtionfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.naviagtionfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var bind: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        navController = Navigation.findNavController(this, R.id.navHostFragment)

        NavigationUI.setupWithNavController(bind.navView, navController)

        NavigationUI.setupWithNavController(bind.bottomNavigationView, navController)

        NavigationUI.setupActionBarWithNavController(this, navController, bind.root)

    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, bind.root)
}