package com.example.elitevisitormanagementsystem


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Password")
    val password: String,
    @SerializedName("username")
    val username: String
)