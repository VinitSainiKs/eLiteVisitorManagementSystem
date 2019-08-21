package com.example.elitevisitormanagementsystem


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Search(
    @SerializedName("ns")
    val ns: Int,
    @SerializedName("pageid")
    val pageid: Int,
    @SerializedName("size")
    val size: Int,
    @SerializedName("snippet")
    val snippet: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("wordcount")
    val wordcount: Int
)