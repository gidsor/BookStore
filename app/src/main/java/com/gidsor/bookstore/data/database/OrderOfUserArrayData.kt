package com.gidsor.bookstore.data.database

import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.GetOrdersTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.collections.ArrayList

object OrderOfUserArrayData {
    private var ordersOfUser: ArrayList<Order> = arrayListOf()

    fun updateOrdersOfUser(user: User) {
        ordersOfUser = arrayListOf()

        val responseOrders = GetOrdersTask().execute(user.id.toString()).get()
        if (!responseOrders.isNull("result")) {
            val ordersType = object  : TypeToken<ArrayList<Order>>() {}.type
            ordersOfUser = Gson().fromJson<ArrayList<Order>>(responseOrders.getJSONArray("result").toString(), ordersType)
        }
    }

    fun getOrdersOfUser(): ArrayList<Order> {
        return ordersOfUser
    }
}