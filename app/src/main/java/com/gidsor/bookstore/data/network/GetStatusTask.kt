package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class GetStatusTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String?): JSONObject {
        return HTTPRequestAPI.status()
    }
}