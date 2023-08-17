package com.example.compuactualfcm.io.response

import com.example.compuactualfcm.model.User

data class LoginResponse (val success: Boolean, val user: User, val accessToken: String)