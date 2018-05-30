package com.gidsor.bookstore.data.model

class Book(val isbn: String,
           val composition: Int,
           val name: String,
           val imageUrl: String,
           val genre: String,
           val author: String,
           val year: Int,
           val publisher: String,
           val description: String,
           val price: Int,
           val language: String,
           var rating: Float)