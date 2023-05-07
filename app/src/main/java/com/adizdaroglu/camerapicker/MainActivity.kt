package com.adizdaroglu.camerapicker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.adizdaroglu.camerapicker.databinding.ActivityMainBinding

const val IMAGE_BITMAP_KEY= "IMAGE_BITMAP"
const val FIRST_NAME_KEY= "FIRST_NAME"
const val EMAIL_KEY= "EMAIL"
const val PASSWORD_KEY= "PASSWORD"
const val WEBSITE_KEY= "WEBSITE"

class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)
        activityBinding.apply {
            relativelayoutAvatar.setOnClickListener {
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
            submitButton.setOnClickListener {
                submitForm()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            activityBinding.apply {
                imageviewAvatar.apply {
                    visibility = View.VISIBLE
                    setImageBitmap(imageBitmap)
                }
                textviewAvatar.visibility = View.GONE
            }
        }
    }

    private fun submitForm() {
        val editTextList = arrayListOf(
            activityBinding.firstNameEditText,
            activityBinding.emailEditText,
            activityBinding.passwordEditText,
            activityBinding.websiteEditText,
        )

        var hasEmptyField = false
        for (editText in editTextList) {
            if (editText.text.toString().isEmpty()) {
                editText.error = "This field is required"
                hasEmptyField = true
            }
        }

        if (hasEmptyField.not() && activityBinding.imageviewAvatar.drawable != null) {
            val intent = Intent(this, ConfirmationActivity::class.java)

            val imageDrawable = activityBinding.imageviewAvatar.drawable
            val bitmap = (imageDrawable as BitmapDrawable).bitmap
            intent.putExtra(IMAGE_BITMAP_KEY, bitmap)

            intent.putExtra(FIRST_NAME_KEY, activityBinding.firstNameEditText.text.toString())
            intent.putExtra(EMAIL_KEY, activityBinding.emailEditText.text.toString())
            intent.putExtra(PASSWORD_KEY, activityBinding.passwordEditText.text.toString())
            intent.putExtra(WEBSITE_KEY, activityBinding.websiteEditText.text.toString())

            startActivity(intent)
        }
    }


    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
    }
}