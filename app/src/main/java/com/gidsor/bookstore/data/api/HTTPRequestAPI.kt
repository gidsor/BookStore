package com.gidsor.bookstore.data.api

import android.util.Log
import com.gidsor.bookstore.utils.AppConstants
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONObject

object HTTPRequestAPI {
    private const val siteUrl: String = AppConstants.URL_REQUEST_SITE_PROXY

    private fun getData(url: String): JSONObject {
        var data = JSONObject()
        val (_, _, result) = url.httpGet().responseString()
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

    fun status() = getData("$siteUrl/api/status")

    fun login(email: String, password: String) = getData("$siteUrl/api/login?email=$email&password=$password")

    fun registration(email: String, password: String, firstname: String, lastname: String, patronymic: String, phone: String)
            = getData("$siteUrl/api/registration?login=$email&password=$password&email=$email" +
            "&firstname=$firstname&lastname=$lastname&patronymic=$patronymic&phone=$phone")

    fun library(user: String, lib: String) = getData("$siteUrl/api/library?user=$user&lib=$lib")

    fun addToLibrary(user: String, lib: String, composition: String)
            = getData("$siteUrl/api/add_to_lib?user=$user&lib=$lib&composition=$composition")

    fun delFromLibrary(user: String, lib: String, composition: String)
            = getData("$siteUrl/api/del_from_lib?user=$user&lib=$lib&composition=$composition")

    fun composition(composition: String = "", author: String = "", title: String = "",
                    genre: String = "", category: String = "", language: String = "")
            = getData("$siteUrl/api/composition?composition=$composition&author=$author" +
            "&title=$title&genre=$genre&category=$category&language=$language")

    fun book(isbn: String = "", composition: String = "") = getData("$siteUrl/api/book?isbn=$isbn&composition=$composition")

    fun user(id: String = "") = getData("$siteUrl/api/user?id=$id")

    fun addToBasket(user: String, isbn: String) = getData("$siteUrl/api/add_to_basket?user=$user&isbn=$isbn")

    fun delFromBasket(user: String, isbn: String) = getData("$siteUrl/api/del_from_basket?user=$user&isbn=$isbn")

    fun createOrder(user: String, card: String, address: String, phone: String, comment: String, type: String)
            = getData("$siteUrl/api/create_order?user=$user&card=$card&address=$address&phone=$phone&comment=$comment&type=$type")

    fun getBasket(user: String) = getData("$siteUrl/api/get_basket?user=$user")

    fun getOrders(user: String) = getData("$siteUrl/api/get_orders?user=$user")

    fun getOrder(user: String, order: String) = getData("$siteUrl/api/get_order?user=$user&order=$order")

    fun review(isbn: String = "", composition: String = "") = getData("$siteUrl/api/review?isbn=$isbn&composition=$composition")

    fun setReview(user: String, composition: String, mark: String, review: String)
            = getData("$siteUrl/api/set_review?user=$user&composition=$composition&mark=$mark&review=$review")
}