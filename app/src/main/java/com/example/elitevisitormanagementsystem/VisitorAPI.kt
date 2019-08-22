package com.example.elitevisitormanagementsystem

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface VisitorAPI {

    @POST("EmployeeLogin")
    fun loginUser(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("UpdateVisitorStatus")
    fun updateVisitorStatus(@Body updateRequest: UpdateRequest): Call<String>

    @GET("/GetVisitors/EmployeeGuid")
    fun getVisitorData(
        @Query("EmployeeGuid") EmployeeGuid: String
    ): Call<List<VisitorResponse>>


}