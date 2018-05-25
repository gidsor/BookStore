package com.gidsor.bookstore.data.db

import com.gidsor.bookstore.data.model.Order

class OrderArrayData {
    companion object {
        private var orders: ArrayList<Order>

        init {
            orders = arrayListOf()
            updateOrders()
        }

        private fun updateOrders() {
            orders = arrayListOf()
//            for (i in 0 until 5) {
//                orders.add(Order("1", BookArrayData.getBooks()[i]))
//            }
        }

        fun addOrder(order: Order) {
            orders.add(order)
        }

        fun removeOrder(order: Order) {
            orders.remove(order)
        }

        fun getOrders(): ArrayList<Order> {
            return orders
        }

        fun getCommonPrice(): Int {
            var price = 0
            for (i in getOrders()) {
                price += i.book.price
            }
            return price
        }
    }
}