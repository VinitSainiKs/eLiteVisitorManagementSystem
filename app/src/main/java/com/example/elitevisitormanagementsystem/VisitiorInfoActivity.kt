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

class VisitiorInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitorinfo)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.106:8009/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkAPI = retrofit.create(VisitorAPI::class.java)


        val visitor: VisitorResponse = intent.getSerializableExtra("response") as VisitorResponse

        textView_nop.text = visitor.name
        textView_email.text = visitor.emailAddress
        textView_meet.text = visitor.meet
        textView_wtm.text = visitor.whomToMeet
        textView_idt.text = visitor.inDateTime
        textView_odt.text = visitor.outDateTime
        textView_git.text = visitor.govtIdname
        textView_gid.text = visitor.govtId
        textView_status.text = visitor.status

        val imageAsBytes =
            Base64.decode(visitor.imageProfile.split(",")[1].toByteArray(), Base64.DEFAULT)
        imageview2_id.setImageBitmap(
            BitmapFactory.decodeByteArray(
                imageAsBytes,
                0,
                imageAsBytes.size
            )
        )

        button_approve.setOnClickListener {
            networkAPI.updateVisitorStatus(UpdateRequest("810100000", visitor.visitorId))
                .enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.body() == "Updated"){
                            textView_status.text = "Approved"
                            textView_status.setBackgroundColor(resources.getColor(R.color.green))

                        }

                        else
                            Toast.makeText(
                                this@VisitiorInfoActivity,
                                "Request Approval Failed",
                                Toast.LENGTH_SHORT
                            ).show()


                    }

                })
        }

        button_reject.setOnClickListener {

            networkAPI.updateVisitorStatus((UpdateRequest("810100002", visitor.visitorId)))
                .enqueue(object : Callback<String> {
                    override fun onFailure(call: Call<String>, t: Throwable) {
                        t.printStackTrace()
                    }

                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.body() == "Updated"){
                            textView_status.text = "Rejected"
                            textView_status.setBackgroundColor(resources.getColor(R.color.red))
                        }
                        else
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
