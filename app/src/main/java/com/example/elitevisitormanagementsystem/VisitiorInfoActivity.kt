package com.example.elitevisitormanagementsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_visitorinfo.*

class VisitiorInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visitorinfo)


        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val meet = intent.getStringExtra("meet")
        val wTM = intent.getStringExtra("wtm")
        val gIT = intent.getStringExtra("git")
        val gID = intent.getStringExtra("gid")
        val status = intent.getStringExtra("status")


        textView_nop.text = name
        textView_email.text = email
        textView_meet.text = meet
        textView_wtm.text = wTM
        textView_git.text = gIT
        textView_gid.text = gID
        textView_status.text = status
    }
}
