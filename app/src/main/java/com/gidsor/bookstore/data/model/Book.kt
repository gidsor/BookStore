package com.gidsor.bookstore.data.model

import com.google.gson.annotations.SerializedName

class Book(var isbn: String,
           var composition: Int,
           var title: String,
           var description: String,
           var language: String,
           var year: Int,
           var price: Int,
           @SerializedName("mark") var rating: Float,
           @SerializedName("envelope") var image: String,
           @SerializedName("ph") var publisher: String,
           var genre: String = "",
           var author: String = "") {
}