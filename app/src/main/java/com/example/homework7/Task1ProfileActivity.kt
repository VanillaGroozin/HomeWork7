package com.example.homework7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Task1ProfileActivity : AppCompatActivity() {
    var login = ""
    var mail = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1_profile)
        login = intent.extras?.getString("LOGIN").toString()
        mail = intent.extras?.getString("EMAIL").toString()
        password = intent.extras?.getString("PASSWORD").toString()
        initializeViews()
    }

    fun initializeViews(){
        findViewById<TextView>(R.id.activity_task1_profile_text_view_login).setText("Login = $login");
        findViewById<TextView>(R.id.activity_task1_profile_text_view_mail).setText("Mail = $mail");
        findViewById<TextView>(R.id.activity_task1_profile_text_view_password).setText("Password = $password");
    }
}