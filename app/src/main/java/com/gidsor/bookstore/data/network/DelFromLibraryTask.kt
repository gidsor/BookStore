package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class DelFromLibraryTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.delFromLibrary(params[0], params[1], params[2])
    }
}