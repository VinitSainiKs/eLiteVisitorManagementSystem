package com.example.elitevisitormanagementsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)



        login_button.setOnClickListener {
            val i = Intent(this@LoginActivity, VisitorListActivity::class.java)
            startActivity(i)
        }

    }

//    fun loginClick(view: View){
//        val i = Intent(this@LoginActivity, VisitorListActivity::class.java)
//        startActivity(i)
//    }

}
