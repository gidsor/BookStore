package com.gidsor.bookstore.data.network

import android.os.AsyncTask
import org.json.JSONObject

class GetBasketTask : AsyncTask<String, Void, JSONObject>() {
    override fun doInBackground(vararg params: String): JSONObject {
        return HTTPRequestAPI.getBasket(params[0])
    }
}