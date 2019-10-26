package com.example.lab_3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.camera_activity.*

class CameraActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_activity)

        name_text.text = intent.getStringExtra("name")
        image.setImageBitmap(intent.getParcelableExtra("image"))
    }
}