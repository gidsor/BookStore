package com.gidsor.bookstore.data.db

import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.network.AddToBasketTask
import com.gidsor.bookstore.data.network.DelFromBasketTask
import com.gidsor.bookstore.data.network.GetBasketTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user

class OrderArrayData {
    companion object {
        private var orders: ArrayList<Order>

        init {
            orders = arrayListOf()
            updateOrders()
        }

        fun updateOrders() {
            orders = arrayListOf()
            var basket = GetBasketTask().execute(user.id.toString()).get()
            if (!basket.isNull("result")) {
                var arr = basket.getJSONArray("result")
                for (i in 0 until arr.length()) {
                    var book = BookArrayData.getBook(arr.getJSONObject(i).getString("isbn"))
                    orders.add(Order(user.id.toString(), book))
                }
            }
        }

        fun addOrder(order: Order) {
            orders.add(order)
            AddToBasketTask().execute(order.id, order.book.isbn)
            updateOrders()
        }

        fun removeOrder(order: Order) {
            orders.remove(order)
            DelFromBasketTask().execute(order.id, order.book.isbn)
            updateOrders()
        }

        fun getOrders(): ArrayList<Order> {
            return orders
        }

        fun getCommonPrice(servieces: Int = 0): Int {
            var price = servieces
            for (i in getOrders()) {
                price += i.book.price
            }
            return price + servieces
        }
    }
}