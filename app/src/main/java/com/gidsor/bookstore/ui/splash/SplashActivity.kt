package com.gidsor.bookstore.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BookArrayData
import com.gidsor.bookstore.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val intent = Intent(this, MainActivity::class.java)

        var isComplete = false

        Thread(Runnable {
            isComplete = BookArrayData.updateBooks()
        }).start()

        Thread(Runnable {
            while (!isComplete) {
                Thread.sleep(10)
            }
            startActivity(intent)
            finish()
        }).start()
    }
}