package com.example.compuactualfcm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.compuactualfcm.PreferenceHelper.set

class MenuActiviy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_activiy)

        //Obtener el id del xml
        val button = findViewById<Button>(R.id.btnEquipos)
        val buttonlogout = findViewById<Button>(R.id.btnLogout)

        //darle funcion al boton para mostrar otra pestaña
        button.setOnClickListener {
            Toast.makeText(this, "Tus Equipos", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, CreateEquipos::class.java)
            startActivity(intent)
        }

        buttonlogout.setOnClickListener {
            clearSessionPreference()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun clearSessionPreference() {
        //val preferences = getSharedPreferences("Login", MODE_PRIVATE);
        //val editor = preferences.edit()
        //editor.putBoolean("session", false)
        //editor.apply()

        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["session"] = false
    }
}