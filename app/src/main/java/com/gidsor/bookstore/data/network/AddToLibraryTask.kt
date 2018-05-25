package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class AddToLibraryTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.add_to_library(params[0], params[1], params[2])
    }
}