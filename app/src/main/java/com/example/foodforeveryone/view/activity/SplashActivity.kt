package com.example.foodforeveryone.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.foodforeveryone.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val loginPageIntent = findViewById<Button>(R.id.get_started)

        loginPageIntent.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }

    }
}