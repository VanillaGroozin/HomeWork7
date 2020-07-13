package com.example.homework7

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi

class Task3ProfileActivity : AppCompatActivity() {
    var RESULT_REQUEST = 1002
    var PERMISSION_CODE = 1000
    var IMAGE_CAPTURE_CODE = 1001
    var imageViewSelfie: ImageView? = null;
    var imageUri: Uri? = null
    var isImageResult: Boolean? = null;
    var editTextFirstName: EditText? = null;
    var editTextLastName: EditText? = null;
    var buttonSend: Button? = null;

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3_profile)
        initializeViews();
        initializeEventListeners()
        isImageResult = false;
    }

    fun initializeViews(){
        imageViewSelfie = findViewById(R.id.activity_task3_profile_image_view_selfie)
        imageViewSelfie?.setImageDrawable(getDrawable(R.drawable.ic_activity_task2_take_photo))
        editTextFirstName = findViewById(R.id.activity_task3_profile_edit_text_first_name)
        editTextLastName = findViewById(R.id.activity_task3_profile_edit_text_last_name)
        buttonSend = findViewById(R.id.activity_task3_profile_button_send)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initializeEventListeners(){
        imageViewSelfie?.setOnClickListener(View.OnClickListener {
            if(!initiateCheckPermissionCamera())
                initiateRequestPermissionsCamera()
            else
                initiateCamera()
        })

        buttonSend?.setOnClickListener(View.OnClickListener {
                setResult(RESULT_REQUEST, Intent().apply {
                    putExtra("FIRST", editTextFirstName?.text.toString())
                    putExtra("LAST", editTextLastName?.text.toString())
                    if(isImageResult!!)
                    putExtra("SELFIE",imageUri.toString())
                })
                finish()
        })
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initiateRequestPermissionsCamera(){
        requestPermissions(Array(1){android.Manifest.permission.CAMERA},PERMISSION_CODE)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initiateCheckPermissionCamera() :Boolean{
        return  checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE->{
                if(grantResults.isNotEmpty()){
                    initiateCamera()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //called when image was captured from camera intent
        when(requestCode){
            IMAGE_CAPTURE_CODE-> {
                if(resultCode == Activity.RESULT_OK) {
                    isImageResult=true
                    imageViewSelfie?.setImageURI(imageUri)
                }
            }
        }
    }

    fun initiateCamera(){
        val values = ContentValues()
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }
}