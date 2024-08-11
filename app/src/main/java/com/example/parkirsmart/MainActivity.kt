package com.example.parkirsmart

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "App started")

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_map -> {
                    // Navigate to MapActivity
                    true
                }
                R.id.menu_report -> {
                    // Navigate to ReportActivity
                    true
                }
                R.id.menu_statistics -> {
                    // Navigate to StatisticsActivity
                    true
                }
                else -> false
            }
        }
    }
}