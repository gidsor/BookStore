package com.gidsor.bookstore.data.database

import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.GetOrdersTask
import java.util.*

object OrderOfUserArrayData {
    private var ordersOfUser: ArrayList<Order> = arrayListOf()

    fun updateOrdersOfUser(user: User) {
        ordersOfUser = arrayListOf()
        val orders = GetOrdersTask().execute(user.id.toString()).get()
        if (!orders.isNull("result")) {
            val arr = orders.getJSONArray("result")
            for (i in 0 until arr.length()) {
                val order = arr.getJSONObject(i)
                val id = order.getString("order")
                val price = order.getInt("price")
                val descriptionPrice = order.getString("description_price")
                val comment = order.getString("comment")
                val dateOrder = order.getString("date_order")
                val dateDelivery = order.getString("date_delivery")
                val address = order.getString("address")
                val phone = order.getString("phone")
                val statusDelivery = order.getString("status_delivery")
                val priceDelivery = order.getInt("price_delivery")
                val descriptionDelivery = order.getString("description_delivery")

                ordersOfUser.add(Order(id, descriptionPrice, comment, dateOrder, address, price,
                        dateDelivery, phone, statusDelivery, priceDelivery, descriptionDelivery))

            }

        }
    }

    fun getOrdersOfUser(): ArrayList<Order> {
        return ordersOfUser
    }
}