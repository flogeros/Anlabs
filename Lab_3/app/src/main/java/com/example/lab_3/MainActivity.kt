package com.example.lab_3

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.support.design.widget.Snackbar


class MainActivity : AppCompatActivity() {
    private var camera = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        photo_button.setOnClickListener {v ->
            try {
                when {
                    name_text_view.text.isEmpty() -> Snackbar.make(v, R.string.empty_name_view, Snackbar.LENGTH_LONG).show()
                    else -> startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), camera)
                }
            } catch (e: Exception){
                Snackbar.make(v, R.string.error_text, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == camera && resultCode == Activity.RESULT_OK) {
            val intent = Intent(this, CameraActivity::class.java)
            intent.putExtra("name", name_text_view.text.toString())
            intent.putExtra("image", data!!.extras.get("data") as Bitmap)
            startActivity(intent) }
    }
}
