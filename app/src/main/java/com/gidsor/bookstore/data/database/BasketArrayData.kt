package com.gidsor.bookstore.data.database

import com.gidsor.bookstore.data.model.BasketItem
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.AddToBasketTask
import com.gidsor.bookstore.data.network.DelFromBasketTask
import com.gidsor.bookstore.data.network.GetBasketTask
import com.gidsor.bookstore.ui.main.MainActivity

object BasketArrayData {
    private var booksOfBasket: ArrayList<BasketItem> = arrayListOf()

    fun updateOrder(user: User) {
        booksOfBasket = arrayListOf()
        val basket = GetBasketTask().execute(user.id.toString()).get()
        if (!basket.isNull("result")) {
            val arr = basket.getJSONArray("result")
            for (i in 0 until arr.length()) {
                val book = BookArrayData.getBook(arr.getJSONObject(i).getString("isbn"))
                val count = arr.getJSONObject(i).getInt("count")
                booksOfBasket.add(BasketItem(user, book, count))
            }
        }
        MainActivity.badge.text = countOfBooks().toString()
    }

    private fun countOfBooks(): Int {
        var count = 0
        for (i in booksOfBasket) {
            count += i.count
        }
        return count
    }

    fun addToBasket(basketItem: BasketItem) {
        booksOfBasket.add(basketItem)
        AddToBasketTask().execute(basketItem.user.id.toString(), basketItem.book.isbn)
        updateOrder(basketItem.user)
    }

    fun removeFromBasket(basketItem: BasketItem) {
        booksOfBasket.remove(basketItem)
        DelFromBasketTask().execute(basketItem.user.id.toString(), basketItem.book.isbn)
        updateOrder(basketItem.user)
    }

    fun getBasket(): ArrayList<BasketItem> {
        return booksOfBasket
    }

    fun getCommonPrice(): Int {
        var price = 0
        for (i in getBasket()) {
            price += i.book.price
        }
        return price
    }
}