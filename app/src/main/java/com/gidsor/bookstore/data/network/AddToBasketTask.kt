package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class AddToBasketTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.add_to_basket(params[0], params[1])
    }
}