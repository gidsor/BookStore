package com.gidsor.bookstore.data.model

class Book(val name: String, val image: Int, val authors: String = "", val year: Int = 2001,
           val publisher: String = "", val description: String = "")