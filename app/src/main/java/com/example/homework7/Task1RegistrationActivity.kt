package com.example.homework7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class Task1RegistrationActivity : AppCompatActivity() {
    private val RESULT_REGISTRATION = 1001
    var editTextLogin:EditText? = null;
    var editTextEmail:EditText? = null
    var editTextPassword:EditText? = null;
    var buttonSend:Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1_registration)
        initializeViews()
        initializeListeners()
    }

    fun initializeViews(){
        editTextLogin = findViewById(R.id.activity_registration_task1_edit_text_login)
        editTextEmail = findViewById(R.id.activity_registration_task1_edit_text_email)
        editTextPassword = findViewById(R.id.activity_registration_task1_edit_text_password)
        buttonSend = findViewById(R.id.activity_registration_task1_button_send)
    }

    fun initializeListeners(){
        buttonSend?.setOnClickListener(View.OnClickListener {
            setResult(RESULT_REGISTRATION, Intent().apply {
                putExtra("LOGIN",editTextLogin?.text.toString())
                putExtra("EMAIL",editTextEmail?.text.toString())
                putExtra("PASSWORD",editTextPassword?.text.toString())
            })
            finish()
        })
    }
}