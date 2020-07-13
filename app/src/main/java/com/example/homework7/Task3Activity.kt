package com.example.homework7

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class Task3Activity : AppCompatActivity() {
    var RESULT_REQUEST = 1002
    var imageViewSelfie: ImageView? = null;
    var editTextFirstName: EditText? = null;
    var editTextLastName: EditText? = null;
    var buttonFillData:Button? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)
        initializeViews()
        initializeListeners()
    }

    fun initializeViews(){
        imageViewSelfie = findViewById(R.id.activity_task3_image_view_selfie)
        imageViewSelfie?.setImageDrawable(getDrawable(R.drawable.ic_activity_task3_preview_selfie))
        editTextFirstName = findViewById(R.id.activity_task3_edit_text_first_name)
        editTextLastName = findViewById(R.id.activity_task3_edit_text_last_name)
        buttonFillData = findViewById(R.id.activity_task3_button_fill_data)
    }

    fun initializeListeners() {
        buttonFillData?.setOnClickListener(View.OnClickListener {
            startActivityForResult(
                Intent(this, Task3ProfileActivity::class.java),
                RESULT_REQUEST
            )
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            RESULT_REQUEST->{
                if (data != null) {
                    if(!data.extras?.getString("FIRST").toString().isNullOrEmpty()){
                        editTextFirstName?.setText(data.extras?.getString("FIRST").toString())
                    }
                    if(!data.extras?.getString("LAST").isNullOrEmpty()){
                        editTextLastName?.setText(data.extras?.getString("LAST").toString())
                    }
                    if(!data.extras?.getString("SELFIE").isNullOrEmpty()){
                        imageViewSelfie?.setImageURI(Uri.parse(data.extras?.getString("SELFIE").toString()))
                    }
                }
            }
        }
    }
}