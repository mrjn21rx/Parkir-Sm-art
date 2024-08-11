package com.example.parkirsmart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val descriptionEditText = findViewById<EditText>(R.id.report_description)
        val submitButton = findViewById<Button>(R.id.report_submit)

        submitButton.setOnClickListener {
            val description = descriptionEditText.text.toString()
            // Kirim laporan ke server
        }
    }
}
