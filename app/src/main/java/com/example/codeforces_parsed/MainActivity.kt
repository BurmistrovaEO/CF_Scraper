package com.example.codeforces_parsed

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val settingsButton : Button = findViewById(R.id.button)
        settingsButton.setOnClickListener{
            val settingsIntent = Intent(this, Settings::class.java)
            this.startActivity(settingsIntent)
        }
    }
}