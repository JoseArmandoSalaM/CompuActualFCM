package com.example.compuactualfcm.io.response

import com.example.compuactualfcm.model.User

data class LoginResponse (val suscces: Boolean, val user: User, val accesToken: String)