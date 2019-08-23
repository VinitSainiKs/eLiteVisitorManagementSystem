package com.example.elitevisitormanagementsystem

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_visitorlist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import okhttp3.internal.http.RealResponseBody
import okhttp3.internal.toImmutableList
import java.text.SimpleDateFormat


class VisitorListActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerView.Adapter<VisitorAdaptor.MyViewHolder>
    lateinit var visitorList: ArrayList<VisitorResponse>
    lateinit var baseVisitorList: ArrayList<VisitorResponse>


    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.1.106:8009/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val networkAPI = retrofit.create(VisitorAPI::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitorlist)

        val toolbar: Toolbar = toolbar
        setSupportActionBar(toolbar)
        toolbar.inflateMenu(R.menu.filter_menu)


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.filter_menu, menu)
        return true

        //return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId


        if (id == R.id.today_item) {
            Toast.makeText(this, "Todays Visitors", Toast.LENGTH_SHORT).show()
            visitorList.filter { visitorResponse ->
                val dateString = visitorResponse.inDateTime

                val date = SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(dateString)

                println("date today list $date")

                date.date == Date().date

            }.also { visitorList.clear() }.toCollection(visitorList)
            adapter.notifyDataSetChanged()
        }


        if (id == R.id.all_item) {
            Toast.makeText(this, "All Visitors", Toast.LENGTH_SHORT).show()
            visitorList.also { it.clear() }.addAll(baseVisitorList)

            println("baseVisitorList ----------->>   $visitorList")

            adapter.notifyDataSetChanged()
        }


        if (id == R.id.upcoming_item){
            Toast.makeText(this, "Upcoming Visitors", Toast.LENGTH_SHORT).show()

            visitorList.filter { visitorResponse ->
                val dateString = visitorResponse.inDateTime
                val date = SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(dateString)
                date.after(Date())
            }.also {
                visitorList.clear()
                println(it.size)
            }.toCollection(visitorList)

            println()

        }



        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        networkAPI
            .getVisitorData(intent.getStringExtra("user-guid")!!)
            .enqueue(object : Callback<List<VisitorResponse>> {
                override fun onFailure(call: Call<List<VisitorResponse>>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<VisitorResponse>>,
                    response: Response<List<VisitorResponse>>
                ) {

                    visitorList = response.body() as ArrayList<VisitorResponse>

                    baseVisitorList = ArrayList(visitorList)




                    adapter = VisitorAdaptor(
                        this@VisitorListActivity,
                        visitorList
                    )

                    rv_id.adapter = adapter
                    rv_id.layoutManager = LinearLayoutManager(this@VisitorListActivity)
                }

            })

    }


//    fun todaysVisitorsList() {
//
//        networkAPI
//            .getVisitorData(intent.getStringExtra("user-guid")!!)
//            .enqueue(object : Callback<List<VisitorResponse>> {
//                override fun onFailure(call: Call<List<VisitorResponse>>, t: Throwable) {
//                    t.printStackTrace()
//                }
//
//                override fun onResponse(
//                    call: Call<List<VisitorResponse>>,
//                    response: Response<List<VisitorResponse>>
//                ) {
//
//                    val filterList = response.body()!!.filter { visitorResponse ->
//                        val dateString = visitorResponse.inDateTime
//
//                        val date1 = SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(dateString)
//                        date1.date == Date().date
//
//                    }
//
//
//                    adapter = VisitorAdaptor(
//                        this@VisitorListActivity,
//                        filterList as ArrayList<VisitorResponse>
//                    )
//
//                    rv_id.adapter = adapter
//                    rv_id.layoutManager = LinearLayoutManager(this@VisitorListActivity)
//                }
//
//            })
//
//    }


}
