package com.example.compuactualfcm.io

import com.example.compuactualfcm.io.response.LoginResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {
    @POST("login")
    fun postLogin(@Query("email") email: String, @Query("password") password: String):
            retrofit2.Call<LoginResponse>

    companion object Factory{
        private const val BASE_URL = "http://compuactual.lat/api/"

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