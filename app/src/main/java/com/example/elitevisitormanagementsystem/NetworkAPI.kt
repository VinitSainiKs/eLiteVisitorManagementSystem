package com.example.elitevisitormanagementsystem

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {
    @GET("api.php")
    fun getWikiData(
        @Query("action") action: String,
        @Query("format") format: String,
        @Query("list") list: String,
        @Query("srsearch") search: String
    ): Call<WikiResponse>
}