package com.example.compuactualfcm.io

import com.example.compuactualfcm.io.response.LoginResponse
import com.example.compuactualfcm.model.Appoiment
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("login")
    fun postLogin(@Query("email") email: String, @Query("password") password: String):
            retrofit2.Call<LoginResponse>

    @GET("proyectos")
    fun getAppoiment(@Header("Authorization") authHeader: String):
            retrofit2.Call<ArrayList<Appoiment>>


    @POST("fcm/token")
    fun postToken(
        @Header("Authorization") authHeader: String,
        @Query("device_token") token: String
    ): retrofit2.Call<Void>

    companion object Factory{
        private const val BASE_URL = "https://compuactual.online/api/"

        fun create(): ApiService{
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()


            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}