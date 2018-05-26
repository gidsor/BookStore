package com.gidsor.bookstore.data.db

import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.network.AddToBasketTask
import com.gidsor.bookstore.data.network.DelFromBasketTask
import com.gidsor.bookstore.data.network.GetBasketTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user

class BasketArrayData {
    companion object {
        private var booksOfBasket: ArrayList<Order>

        init {
            booksOfBasket = arrayListOf()
            updateOrder()
        }

        fun updateOrder() {
            booksOfBasket = arrayListOf()
            var basket = GetBasketTask().execute(user.id.toString()).get()
            if (!basket.isNull("result")) {
                var arr = basket.getJSONArray("result")
                for (i in 0 until arr.length()) {
                    var book = BookArrayData.getBook(arr.getJSONObject(i).getString("isbn"))
                    booksOfBasket.add(Order(user.id.toString(), book))
                }
            }
        }

        fun addToBasket(order: Order) {
            booksOfBasket.add(order)
            AddToBasketTask().execute(order.id, order.book.isbn)
            updateOrder()
        }

        fun removeFromBasket(order: Order) {
            booksOfBasket.remove(order)
            DelFromBasketTask().execute(order.id, order.book.isbn)
            updateOrder()
        }

        fun getBasket(): ArrayList<Order> {
            return booksOfBasket
        }

        fun getCommonPrice(servieces: Int = 0): Int {
            var price = servieces
            for (i in getBasket()) {
                price += i.book.price
            }
            return price + servieces
        }
    }
}