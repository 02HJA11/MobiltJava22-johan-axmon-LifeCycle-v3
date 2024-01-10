package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var sharedPreferences: SharedPreferences


class MainActivity2 : AppCompatActivity() {
    val logout = Intent(this@MainActivity2, MainActivity::class.java)


    private lateinit var bottomNav: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.cursedyeahno).into(imageView)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.home
        bottomNav.setOnItemSelectedListener { item ->
            navigateToActivity(item.itemId)
            true
        }
    }
//made following a guide
    private fun navigateToActivity(itemId: Int) {
        val intent = when (itemId) {
            R.id.home -> Intent(this, MainActivity2::class.java)
            R.id.gender -> Intent(this, GenderActivity::class.java)
            R.id.information -> Intent(this, PersonalActivity::class.java)
            R.id.logout -> {
                logOut()
                null
            }
            else -> null
        }
        if (intent != null) {
            startActivity(intent)
        }
    }

    private fun logOut() {
        sharedPreferences = getSharedPreferences("Myprefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
        startActivity(logout)
        finish()
    }
   }
