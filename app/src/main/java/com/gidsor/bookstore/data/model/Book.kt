package com.gidsor.bookstore.data.model

class Book(var isbn: String, var composition: Int, var title: String, var envelope: String, var description: String, var language: String, var year: Int, var mark: Float, var ph: String, var price: Int) {
    var genre: String = ""
    var author: String = ""
}