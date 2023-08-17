package com.example.compuactualfcm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.compuactualfcm.R
import com.example.compuactualfcm.io.ApiService
import com.example.compuactualfcm.model.Appoiment
import com.example.compuactualfcm.util.PreferenceHelper
import com.example.compuactualfcm.util.PreferenceHelper.get
import com.example.compuactualfcm.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateEquipos : AppCompatActivity() {

    private val apiService: ApiService by lazy {
        ApiService.create()
    }
    private val preferences by lazy {
        PreferenceHelper.defaultPrefs(this)
    }
    private var adapter = AppoitmentAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_equipos)
        //Obtener RecyclerView del xml
        val rvapooint = findViewById<RecyclerView>(R.id.rviewappoit)
        loadAppoinments()
        //Asignar la funcion liner que nos permitira utilizar el Recycler a nuestra manera
        rvapooint.layoutManager = LinearLayoutManager(this) //GritayoutManager
        rvapooint.adapter = adapter
    }
    private fun loadAppoinments(){
        val accessToken = preferences["token", ""]
        val call = apiService.getAppoiment("Bearer $accessToken")
        call.enqueue(object: Callback<ArrayList<Appoiment>>{
            override fun onResponse(
                call: Call<ArrayList<Appoiment>>,
                response: Response<ArrayList<Appoiment>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        adapter.appointments = it
                        adapter.notifyDataSetChanged()
                    }

                }
            }

            override fun onFailure(call: Call<ArrayList<Appoiment>>, t: Throwable) {
                toast(t.localizedMessage)
            }

        })
    }
}