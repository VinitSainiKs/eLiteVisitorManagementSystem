package com.example.elitevisitormanagementsystem


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("LoginStatus")
    val loginStatus: String,
    @SerializedName("UserGuid")
    val userGuid: String,
    @SerializedName("Username")
    val username: String
)