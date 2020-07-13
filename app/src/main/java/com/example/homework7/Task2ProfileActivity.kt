package com.example.homework7

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class Task2ProfileActivity : AppCompatActivity() {

    var imageViewSelfie:ImageView? = null;
    var textViewUser:TextView? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2_profile)
        initializeViews()
    }

    fun initializeViews(){
        imageViewSelfie = findViewById(R.id.activity_task2_profile_image_view_selfie)
        imageViewSelfie?.setImageURI(Uri.parse(intent.extras?.getString("SELFIE").toString()))
        textViewUser = findViewById(R.id.activity_task2_profile_text_view_user)
        textViewUser?.setText("${intent.extras?.getString("FIRST").toString()} ${intent.extras?.getString("LAST").toString()}")
    }
}