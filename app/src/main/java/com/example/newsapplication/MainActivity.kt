package com.example.newsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.newsapplication.ui.home.HomePage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler(Looper.getMainLooper()).postDelayed({
            gotoHomePage()
        },2000)
    }

    private fun gotoHomePage() {
     val intent = Intent(this, HomePage::class.java)
        startActivity(intent)
        finish()
    }
}