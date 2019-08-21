package com.example.elitevisitormanagementsystem


import com.google.gson.annotations.SerializedName

data class VisitorResponse(
    @SerializedName("City")
    val city: Any,
    @SerializedName("Country")
    val country: Any,
    @SerializedName("EmailAddress")
    val emailAddress: String,
    @SerializedName("GovtId")
    val govtId: String,
    @SerializedName("GovtIdImage")
    val govtIdImage: String,
    @SerializedName("GovtIdNumber")
    val govtIdNumber: Any,
    @SerializedName("GovtIdname")
    val govtIdname: String,
    @SerializedName("ImageProfile")
    val imageProfile: String,
    @SerializedName("InDateTime")
    val inDateTime: String,
    @SerializedName("Meet")
    val meet: String,
    @SerializedName("Name")
    val name: String,
    @SerializedName("OutDateTime")
    val outDateTime: String,
    @SerializedName("Purpose")
    val purpose: String,
    @SerializedName("RequestedDate")
    val requestedDate: Any,
    @SerializedName("Status")
    val status: String,
    @SerializedName("VisitorId")
    val visitorId: String,
    @SerializedName("VisitorMainId")
    val visitorMainId: String,
    @SerializedName("WhomToMeet")
    val whomToMeet: String
)