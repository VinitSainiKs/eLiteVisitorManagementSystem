package com.example.elitevisitormanagementsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_visitorlist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class VisitorListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitorlist)

        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.106:8009/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val networkAPI = retrofit.create(VisitorAPI::class.java)

        networkAPI
            .getVisitorData(intent.getStringExtra("user-guid")!!)
            .enqueue(object: Callback<List<VisitorResponse>>{
                override fun onFailure(call: Call<List<VisitorResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<VisitorResponse>>,
                    response: Response<List<VisitorResponse>>
                ) {

                    println("success "+response.body())

                    val adapter = VisitorAdaptor(this@VisitorListActivity,
                        response.body() as ArrayList<VisitorResponse>
                    )
                    rv_id.adapter = adapter
                    rv_id.layoutManager = LinearLayoutManager(this@VisitorListActivity)

                }

            })




    }




}
