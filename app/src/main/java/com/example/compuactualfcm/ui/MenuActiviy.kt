package com.example.compuactualfcm.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.compuactualfcm.R
import com.example.compuactualfcm.io.ApiService
import com.example.compuactualfcm.util.PreferenceHelper
import com.example.compuactualfcm.util.PreferenceHelper.get
import com.example.compuactualfcm.util.PreferenceHelper.set
import com.example.compuactualfcm.util.toast
import com.google.firebase.installations.FirebaseInstallations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MenuActiviy : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_activiy)

       val storeToken = intent.getBooleanExtra("store_token",false)
        if(storeToken) {
            storeToken()
        }

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

    private fun storeToken(){
        val accessToken = preferences["accessToken",""]
        val authHeader = "Bearer $accessToken"

        FirebaseInstallations.getInstance().getToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val deviceToken = task.result?.token
                    if (deviceToken != null) {
                        val call = apiService.postToken(authHeader,deviceToken)
                        call.enqueue(object: Callback<Void> {
                            override fun onResponse(call: retrofit2.Call<Void>, response: Response<Void>) {
                                if(response.isSuccessful) {
                                    Log.d(TAG, "Token registrado correctamenente")
                                }else{
                                    Log.d(TAG,"Hubo un problema con el registro")
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                toast(t.localizedMessage)
                            }

                        })

                    }
                } else {
                    Log.d("FCMServices", "Error al obtener el token")
                }
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

    companion object {
        private const val TAG = "MenuActiviy"
    }
}