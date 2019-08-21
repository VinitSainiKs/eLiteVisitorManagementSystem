package com.example.elitevisitormanagementsystem

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VisitorInfoApi {

    @GET("/GetVisitors/EmployeeGuid")
    fun getVisitorData(
        @Query("EmployeeGuid") EmployeeGuid: String
    ): Call<VisitorResponse>


}