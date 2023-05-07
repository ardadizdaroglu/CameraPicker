package com.adizdaroglu.camerapicker

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adizdaroglu.camerapicker.databinding.ActivityConfirmationBinding

class ConfirmationActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityConfirmationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityConfirmationBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        getAllDataFromIntentAndSet()
    }

    private fun getAllDataFromIntentAndSet() {
        val bitmap = intent.getParcelableExtra<Bitmap>(IMAGE_BITMAP_KEY)
        val firstName = intent.getStringExtra(FIRST_NAME_KEY)
        val email = intent.getStringExtra(EMAIL_KEY)
        val website = intent.getStringExtra(WEBSITE_KEY)

        activityBinding.apply {
            helloTitle.text = "Hello, $firstName!"
            imageviewAvatarConfirmation.setImageBitmap(bitmap)
            websiteTextview.text = website
            firstnameTextview.text = firstName
            emailTextview.text = email
        }

    }
}