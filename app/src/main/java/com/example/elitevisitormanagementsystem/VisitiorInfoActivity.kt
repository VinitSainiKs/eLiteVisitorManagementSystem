package com.example.elitevisitormanagementsystem

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_visitorinfo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class VisitiorInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitorinfo)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.106:8009/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkAPI = retrofit.create(VisitorAPI::class.java)


        val visitor: VisitorResponse = intent.getSerializableExtra("response") as VisitorResponse

        userName.text = visitor.name
        email.text = visitor.emailAddress
        meet.text = visitor.meet
        tv_whom_to_meet.text = visitor.whomToMeet
        inDateTime.text = visitor.inDateTime
        outDateTime.text = visitor.outDateTime
        govtIdType.text = visitor.govtIdname
        govtID.text = visitor.govtId
        tv_status.text = visitor.status

        val imageAsBytes =
            Base64.decode(visitor.imageProfile.split(",")[1].toByteArray(), Base64.DEFAULT)
        profileImage.setImageBitmap(
            BitmapFactory.decodeByteArray(
                imageAsBytes,
                0,
                imageAsBytes.size
            )
        )

        approveBtn.setOnClickListener {
            networkAPI.updateVisitorStatus(UpdateRequest("810100000", visitor.visitorId))
                .enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.body() == "Updated") {
                            tv_status.text = "Approved"
                            tv_status.setTextColor(resources.getColor(R.color.green))
                            Toast.makeText(
                                this@VisitiorInfoActivity,
                                "Status Approved Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()

                        } else
                            Toast.makeText(
                                this@VisitiorInfoActivity,
                                "Request Approval Failed",
                                Toast.LENGTH_SHORT
                            ).show()


                    }

                })
        }

        rejectBtn.setOnClickListener {

            networkAPI.updateVisitorStatus((UpdateRequest("810100002", visitor.visitorId)))
                .enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.body() == "Updated") {
                            tv_status.text = "Rejected"
                            tv_status.setTextColor(resources.getColor(R.color.red))
                            Toast.makeText(
                                this@VisitiorInfoActivity,
                                "Status Rejected Successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            finish()
                        } else
                            Toast.makeText(
                                this@VisitiorInfoActivity,
                                "Request Rejection Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                    }

                })

        }

    }
}
