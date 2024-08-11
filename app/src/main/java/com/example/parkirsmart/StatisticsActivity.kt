package com.example.parkirsmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView

class StatisticsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val statisticsTextView = findViewById<TextView>(R.id.statistics_text)
        // Ambil dan tampilkan statistik dari server
    }
}