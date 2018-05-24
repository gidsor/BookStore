package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class CompositionTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.composition(params[0], params[1], params[2], params[3], params[4], params[5])
    }
}