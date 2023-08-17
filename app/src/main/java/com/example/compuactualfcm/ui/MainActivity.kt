package com.example.compuactualfcm.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.compuactualfcm.R
import com.example.compuactualfcm.io.ApiService
import com.example.compuactualfcm.io.response.LoginResponse
import com.example.compuactualfcm.util.PreferenceHelper
import com.example.compuactualfcm.util.PreferenceHelper.get
import com.example.compuactualfcm.util.PreferenceHelper.set
import com.example.compuactualfcm.util.toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.installations.FirebaseInstallations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }

    val lay: LinearLayout by lazy { findViewById(R.id.mainlayout) }
    private val snackBar by lazy {
        Snackbar.make(lay, R.string.press_back_again,Snackbar.LENGTH_SHORT)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstallations.getInstance().getToken(true)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val deviceToken = task.result?.token
                    if (deviceToken != null) {
                        Log.d("MSFMee", deviceToken)
                    }
                } else {
                    Log.d("FCMServices", "Error al obtener el token")
                }
            }



        //extraer id del xml
        val button = findViewById<Button>(R.id.tvRegister)


        //Validar si ya esta logueado con sharePreference, sqlite, datos en memoria
        //val preferences = getSharedPreferences("Login", MODE_PRIVATE);
        //val session = preferences.getBoolean("session", false)

        val preferences = PreferenceHelper.defaultPrefs(this)
        if(preferences["token", ""].contains("."))
            gotoMenuActiviy()


        //darle una funccion al boton
        button.setOnClickListener {
            //validar el login con la bd
            perfomLogin()
        }
    }

    private fun perfomLogin() {
        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etPassword)


       val call = apiService.postLogin(email.text.toString().trim(), password.text.toString().trim())
        call.enqueue(object: Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if(response.isSuccessful){
                    val loginRespon = response.body()
                    if (loginRespon == null) {
                        toast("Las credenciales son incorrectas")
                        return
                    }

                    if (loginRespon.success){
                        createSessionPreference(loginRespon.accessToken)
                        gotoMenuActiviy()
                    }else{
                        toast("Las credenciales son incorrectas")
                    }
                }else{
                    toast("Credenciales incorrectas")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                toast(t.localizedMessage)
            }

        })
    }

    private fun createSessionPreference(accessToken: String) {
       // val preferences = getSharedPreferences("Login", MODE_PRIVATE);
        //val editor = preferences.edit()
        //editor.putBoolean("session", true)
        //editor.apply()

        val preferences = PreferenceHelper.defaultPrefs(this)
        preferences["token"] = accessToken

        }

    private fun gotoMenuActiviy(isUserInput: Boolean = false) {
        Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MenuActiviy::class.java)

        if(isUserInput){
            intent.putExtra("store_token",true)
        }
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




