package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView

private lateinit var sp: SharedPreferences

class GenderActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView
    val logout = Intent(this@GenderActivity, MainActivity::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)
        sp = getSharedPreferences("My Prefs", Context.MODE_PRIVATE)
        val rm = findViewById<RadioButton>(R.id.radioButtonMale)
        val rf = findViewById<RadioButton>(R.id.radioButtonFemale)
        val ro = findViewById<RadioButton>(R.id.radioButtonOther)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.gender

        val imc = sp.getBoolean("imc", false)
        val ifc = sp.getBoolean("ifc", false)
        val ioc = sp.getBoolean("ioc", false)
        rm.isChecked = imc
        rf.isChecked = ifc
        ro.isChecked = ioc
        rm.setOnClickListener {
            sp.edit().putBoolean("imc", true).apply()
            sp.edit().putBoolean("ifc", false).apply()
            sp.edit().putBoolean("ioc", false).apply()
        }
        rf.setOnClickListener {
            sp.edit().putBoolean("ifc", true).apply()
            sp.edit().putBoolean("imc", false).apply()
            sp.edit().putBoolean("ioc", false).apply()
        }
        ro.setOnClickListener {
            sp.edit().putBoolean("ioc", true).apply()
            sp.edit().putBoolean("ifc", false).apply()
            sp.edit().putBoolean("imc", false).apply()
        }


        bottomNav.setOnItemSelectedListener { item ->
            navigateToActivity(item.itemId)
            true
        }

    }

    override fun onPause() {
        super.onPause()
        val editor = sp.edit()
        val rm = findViewById<RadioButton>(R.id.radioButtonMale)
        val rf = findViewById<RadioButton>(R.id.radioButtonFemale)
        val ro = findViewById<RadioButton>(R.id.radioButtonOther)
        editor.putBoolean("imc", rm.isChecked)
        editor.putBoolean("ifc", rf.isChecked)
        editor.putBoolean("ioc", ro.isChecked)
        editor.apply()
    }
    //made following a guide , essentially the whole nav menu is.
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
        startActivity(logout)
        finish()
    }

}