package com.example.foodforeveryone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val loginPageIntent = findViewById<Button>(R.id.get_started)

        loginPageIntent.setOnClickListener{

            val Intent = Intent(this,LoginActivity::class.java)

            startActivity(Intent)

        }

    }
}