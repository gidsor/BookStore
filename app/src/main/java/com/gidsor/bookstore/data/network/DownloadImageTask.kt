package com.gidsor.bookstore.data.network

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.net.URL

class DownloadImageTask(private var imageView: ImageView) : AsyncTask<String, Void, Bitmap>() {

    override fun doInBackground(vararg params: String?): Bitmap {
        val urlImage = params[0]
        return BitmapFactory.decodeStream(URL(urlImage).openStream())
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imageView.setImageBitmap(result)
    }
}