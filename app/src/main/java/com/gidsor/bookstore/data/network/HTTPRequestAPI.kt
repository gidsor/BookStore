package com.gidsor.bookstore.data.network

import android.util.Log
import com.gidsor.bookstore.data.model.User
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.apache.commons.lang.StringEscapeUtils
import org.json.JSONArray
import org.json.JSONObject

class HTTPRequestAPI {
    companion object {
        fun getStatus(): JSONObject {
            val url = "http://212.47.240.244/api/status"
            val (request, response, result) = url.httpGet().responseString()
            var data = JSONObject()
            when (result) {
                is Result.Failure -> {
                    Log.i("HTTPRequestAPI", result.getException().toString())
                }
                is Result.Success -> {
                    data = JSONObject(result.get())
                    Log.i("HTTPRequestAPI", data.toString())
                }
            }
            Log.i("HTTPRequestAPI", data.toString())
            return data
        }
    }
}