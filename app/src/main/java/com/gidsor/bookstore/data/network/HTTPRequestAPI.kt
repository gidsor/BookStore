package com.gidsor.bookstore.data.network

import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject

class HTTPRequestAPI {
    companion object {

        private fun getData(url: String): JSONObject {
            var data = JSONObject()
            val (request, response, result) = url.httpGet().responseString()
            when (result) {
                is Result.Failure -> {
                    Log.i(url, result.getException().toString())
                }
                is Result.Success -> {
                    data = JSONObject(result.get())
                    Log.i(url, data.toString())
                }
            }
            return data
        }

        fun status(): JSONObject {
            val url = "http://212.47.240.244/api/status"
            return getData(url)
        }

        fun login(email: String, password: String): JSONObject {
            val url = "http://212.47.240.244/api/login?email=$email&password=$password"
            return getData(url)
        }

        fun registration(email: String, password: String, firstname: String, lastname: String,
                         patronymic: String, phone: String): JSONObject {
            val url = "http://212.47.240.244/api/registration?login=$email&password=$password&email=$email" +
                    "&firstname=$firstname&lastname=$lastname&patronymic=$patronymic&phone=$phone"
            return getData(url)
        }

        fun library(user: String, lib: String): JSONObject {
            val url = "http://212.47.240.244/api/library?user=$user&lib=$lib"
            return getData(url)
        }

        fun add_to_library(user: String, lib: String, composition: String): JSONObject {
            val url = "http://212.47.240.244/api/add_to_lib?user=$user&lib=$lib&composition=$composition"
            return getData(url)
        }

        fun del_from_library(user: String, lib: String, composition: String): JSONObject {
            val url = "http://212.47.240.244/api/del_from_lib?user=$user&lib=$lib&composition=$composition"
            return getData(url)
        }

        fun review(isbn: String = ""): JSONObject {
            val url = "http://212.47.240.244/api/review?isbn=$isbn"
            return getData(url)
        }

        fun composition(composition: String = "", author: String = "", title: String = "",
                        genre: String = "", category: String = "", language: String = ""): JSONObject {
            val url = "http://212.47.240.244/api/composition?composition=$composition&author=$author" +
                    "&title=$title&genre=$genre&category=$category&language=$language"
            return getData(url)
        }

        fun book(isbn: String = "", composition: String = ""): JSONObject {
            val url = "http://212.47.240.244/api/book?isbn=$isbn&composition=$composition"
            return getData(url)
        }

        fun user(id: String = ""): JSONObject {
            val url = "http://212.47.240.244/api/user?id=$id"
            return getData(url)
        }

        fun add_to_basket(user: String, isbn: String): JSONObject {
            val url = "http://212.47.240.244/api/add_to_basket?user=$user&isbn=$isbn"
            return getData(url)
        }

        fun del_from_basket(user: String, isbn: String): JSONObject {
            val url = "http://212.47.240.244/api/del_from_basket?user=$user&isbn=$isbn"
            return getData(url)
        }

        fun create_order(user: String, card: String, address: String, phone: String,
                         comment: String, type: String): JSONObject {
            val url = "http://212.47.240.244/api/create_order?user=$user&card=$card" +
                    "&address=$address&phone=$phone&comment=$comment&type=$type"
            return getData(url)
        }

        fun get_basket(user: String): JSONObject {
            val url = "http://212.47.240.244/api/get_basket?user=$user"
            return getData(url)
        }

        fun get_orders(user: String): JSONObject {
            val url = "http://212.47.240.244/api/get_orders?user=$user"
            return getData(url)
        }
    }
}