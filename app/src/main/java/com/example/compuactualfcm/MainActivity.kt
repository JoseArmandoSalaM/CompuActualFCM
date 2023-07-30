package com.example.compuactualfcm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.compuactualfcm.PreferenceHelper.get
import com.example.compuactualfcm.PreferenceHelper.set
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    val lay: LinearLayout by lazy { findViewById(R.id.mainlayout) }
    private val snackBar by lazy {
        Snackbar.make(lay,R.string.press_back_again,Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //extraer id del xml
        val button = findViewById<Button>(R.id.tvRegister)

        //Validar si ya esta logueado con sharePreference, sqlite, datos en memoria
        //val preferences = getSharedPreferences("Login", MODE_PRIVATE);
        //val session = preferences.getBoolean("session", false)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["session", false])
            gotoMenuActiviy()

        //darle una funccion al boton
        button.setOnClickListener {
            //validar el login con la bd

            createSessionPreference()
            gotoMenuActiviy()
        }
    }

    private fun createSessionPreference() {
       // val preferences = getSharedPreferences("Login", MODE_PRIVATE);
        //val editor = preferences.edit()
        //editor.putBoolean("session", true)
        //editor.apply()

        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = true

    }

    private fun gotoMenuActiviy() {
        Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MenuActiviy::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
       if(snackBar.isShown)
        super.onBackPressed()
        else
            snackBar.show()
    }
}




