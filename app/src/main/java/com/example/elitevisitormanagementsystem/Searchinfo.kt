package com.example.elitevisitormanagementsystem


import com.google.gson.annotations.SerializedName

data class Searchinfo(
    @SerializedName("totalhits")
    val totalhits: Int
)