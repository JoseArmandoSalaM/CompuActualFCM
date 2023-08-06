package com.example.compuactualfcm.io

import com.example.compuactualfcm.model.login
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    fun postLogin(@Query("Email") email: String, @Query("Password") password: String):
            retrofit2.Call<ArrayList<login>>
}