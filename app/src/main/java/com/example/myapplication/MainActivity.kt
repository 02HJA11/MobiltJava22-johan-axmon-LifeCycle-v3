package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    val cUN = "lemon";
    val cWord = "lemon";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = findViewById<EditText>(R.id.username)
        val pass = findViewById<EditText>(R.id.password)
        val btn = findViewById<Button>(R.id.button)
        val next = Intent(this@MainActivity, MainActivity2::class.java)
        btn.setOnClickListener {
            val eu = user.text.toString()
            val ePass = pass.text.toString()
            if (eu == cUN && ePass == cWord) {
                startActivity(next)
            } else {
                Toast.makeText(this@MainActivity, "please check password and username", Toast.LENGTH_SHORT).show()
            }

        }
    }
    
}