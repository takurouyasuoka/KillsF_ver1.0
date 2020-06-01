package com.takurou.android.killsf

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_open.*

class OpenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open)

        Handler(mainLooper).postDelayed({
            val moviePath = Uri.parse("android.resource://" + packageName + "/" + R.raw.killsfopen1)
            videoView.setVideoURI(moviePath)

            videoView.setOnPreparedListener{
                videoView.start()
                videoView.setMediaController(MediaController(this))
            }

            videoView.setOnCompletionListener {
                val intent = Intent(this@OpenActivity,MainActivity::class.java)
                startActivity(intent)
                finish()
            }

        },200)
    }
}