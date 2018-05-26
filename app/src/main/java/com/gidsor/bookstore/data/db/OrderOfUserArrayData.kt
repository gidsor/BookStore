package com.gidsor.bookstore.data.db

import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.network.AddToBasketTask
import com.gidsor.bookstore.data.network.DelFromBasketTask
import com.gidsor.bookstore.data.network.GetBasketTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user

class OrderOfUserArrayData {
    companion object {
        private var ordersOfUser: ArrayList<Order>

        // TODO make class with api get_order_of_user
        init {
            ordersOfUser = arrayListOf()
            updateOrdersOfUser()
        }

        fun updateOrdersOfUser() {
            ordersOfUser = arrayListOf()
//            var basket = GetBasketTask().execute(user.id.toString()).get()
//            if (!basket.isNull("result")) {
//                var arr = basket.getJSONArray("result")
//                for (i in 0 until arr.length()) {
//                    var book = BookArrayData.getBook(arr.getJSONObject(i).getString("isbn"))
//                    orders.add(Order(user.id.toString(), book))
//                }
//            }
        }

        fun getOrdersOfUser(): ArrayList<Order> {
            return ordersOfUser
        }
    }
}