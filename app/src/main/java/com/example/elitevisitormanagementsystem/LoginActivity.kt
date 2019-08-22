package com.example.elitevisitormanagementsystem

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        val interceptor = HttpLoggingInterceptor()
        interceptor.apply{ interceptor.level = HttpLoggingInterceptor.Level.HEADERS }
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://192.168.1.106:8009/")
            .client(client)
            .build()

        val visitorAPI = retrofit.create(VisitorAPI::class.java)

        input_username.setText("rakesh@gmail.com")
        input_password.setText("12345")


        button_signin.setOnClickListener {
            val progressDialog = ProgressDialog.show(this@LoginActivity, "Please Wait", "Logging In")
            visitorAPI.loginUser(
                LoginRequest(
                    input_password.text.toString(),
                    input_username.text.toString()
                )
            ).enqueue(
                object : Callback<LoginResponse> {
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        t.printStackTrace()
                        progressDialog.dismiss()
                        Toast.makeText(this@LoginActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                        println("error")
                    }

                    override fun onResponse(
                        call: Call<LoginResponse>,
                        response: Response<LoginResponse>
                    ) {
                        println("isExecuted "+call.isExecuted)
                        println("isSuccessful ${response.isSuccessful}")
                        println("response ${response.body()}")
                        progressDialog.dismiss()
                        if (response.body()?.loginStatus == "True") {
                            val i = Intent(this@LoginActivity, VisitorListActivity::class.java)
                            i.putExtra("user-guid",response.body()!!.userGuid)
                            startActivity(i)

                        }else{
                            Toast.makeText(this@LoginActivity,"Login Failed", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            )
        }

    }

//    fun loginClick(view: View){
//        val i = Intent(this@LoginActivity, VisitorListActivity::class.java)
//        startActivity(i)
//    }

}
