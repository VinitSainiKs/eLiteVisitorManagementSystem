package com.example.elitevisitormanagementsystem


import com.google.gson.annotations.SerializedName

data class UpdateRequest(
    @SerializedName("Status")
    val status: String,
    @SerializedName("VisitorGuid")
    val visitorGuid: String
)