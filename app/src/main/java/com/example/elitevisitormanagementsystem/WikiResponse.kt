package com.example.elitevisitormanagementsystem


import com.google.gson.annotations.SerializedName

data class WikiResponse(
    @SerializedName("batchcomplete")
    val batchcomplete: String,
    @SerializedName("continue")
    val continueX: Continue,
    @SerializedName("query")
    val query: Query
)