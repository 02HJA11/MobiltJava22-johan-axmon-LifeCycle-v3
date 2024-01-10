package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView


class PersonalActivity : AppCompatActivity() {
    private lateinit var sp: SharedPreferences
    private lateinit var bottomNav: BottomNavigationView
    val logout = Intent(this@PersonalActivity, MainActivity::class.java)
    val editTextName = findViewById<EditText>(R.id.editTextName)
    val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
    val editTextEmail = findViewById<EditText>(R.id.editTextEmail)



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal)
        sp = getSharedPreferences("My Prefs", Context.MODE_PRIVATE);


        val name = sp.getString("name", "")
        val phone = sp.getString("phone", "")
        val email = sp.getString("email", "")
        editTextName.setText(name)
        editTextPhone.setText(phone)
        editTextEmail.setText(email)
        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.information
        bottomNav.setOnItemSelectedListener { item ->
            navigateToActivity(item.itemId)
            true
        }
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }


    override fun onDestroy() {
        super.onDestroy()
        saveData()
    }

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
    private fun saveData() {
        val nameToSave = editTextName.text.toString()
        val phoneToSave = editTextPhone.text.toString()
        val emailToSave = editTextEmail.text.toString()
        val editor = sp.edit()
        editor.putString("name", nameToSave)
        editor.putString("phone", phoneToSave)
        editor.putString("email", emailToSave)
        editor.apply()
    }

}