package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class DelFromBasketTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.del_from_basket(params[0], params[1])
    }
}