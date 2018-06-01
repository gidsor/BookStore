package com.gidsor.bookstore.data.model

data class BasketItem(var user: User,
                      var book: Book,
                      val count: Int)