package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class LoginTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.login(params[0], params[1])
    }
}