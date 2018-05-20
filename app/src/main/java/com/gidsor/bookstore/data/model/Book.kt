package com.gidsor.bookstore.data.model

class Book(val name: String, val image: Int, val genre: String = "", val authors: String = "", val year: Int = 2001,
           val publisher: String = "", val description: String = "", val price: Int = 1234, val rating: String = "4.0")