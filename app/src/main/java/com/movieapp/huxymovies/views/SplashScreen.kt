package com.movieapp.huxymovies.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity
            val i = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(i)

            // close this activity
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    companion object {

        const val SPLASH_TIME_OUT = 5000
    }

}


