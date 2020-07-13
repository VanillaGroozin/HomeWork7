package com.example.homework7
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Task1Activity: AppCompatActivity()  {
    private val RESULT_REGISTRATION = 1001
    var buttonRegistration: Button? = null;
    var buttonProfile: Button? = null;
    var login = ""
    var mail = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)
        initializeViews()
        initializeListeners()
    }

    fun initializeViews(){
        buttonRegistration = findViewById(R.id.activity_task1_button_registration)
        buttonProfile = findViewById(R.id.activity_task1_button_profile)
    }

    fun initializeListeners(){
        buttonRegistration?.setOnClickListener(View.OnClickListener {
            startActivityForResult(Intent(this,Task1RegistrationActivity::class.java), RESULT_REGISTRATION)
        })
        buttonProfile?.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, Task1ProfileActivity::class.java).apply {
                putExtra("LOGIN", login)
                putExtra("EMAIL",mail)
                putExtra("PASSWORD",password)
            }
            startActivity(intent)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_REGISTRATION->{
                if (data != null) {
                    login = data.extras?.getString("LOGIN").toString();
                    mail = data.extras?.getString("EMAIL").toString();
                    password = data.extras?.getString("PASSWORD").toString();
                }
            }
        }
    }
}