package com.gidsor.bookstore.data.db

import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.BookTask
import com.gidsor.bookstore.data.network.CompositionTask
import org.json.JSONObject

class BookArrayData {
    companion object {
        private var books: ArrayList<Book>
        private var genres: MutableSet<String>

        init {
            books = arrayListOf()
            genres = mutableSetOf()
            updateBooks()
        }

        fun updateBooks() {
            val response: JSONObject = BookTask().execute("", "").get()
            if (response.has("status") && response["status"] == "ok") {
                books = arrayListOf()
                genres = mutableSetOf()
                val result = response.getJSONArray("result")
                for (i in 0 until result.length()) {
                    val bookItem = result.getJSONObject(i)

                    val language = bookItem.getString("language")
                    val publisher = bookItem.getString("ph")
                    val year = bookItem.getInt("year")
                    val price = bookItem.getInt("price")
                    val description = bookItem.getString("description")
                    val image = "http://212.47.240.244/images/" + bookItem.getString("envelope")
                    val isbn = bookItem.getString("isbn")

                    val composition = bookItem.getInt("composition")
                    val compositionJSON = getComposition(composition)

                    val author = compositionJSON.getString("author")
                    val genre = compositionJSON.getString("genre")
                    val name = compositionJSON.getString("title")

                    val rating = 5.0f

                    books.add(Book(isbn, composition, name, image, genre, author, year, publisher, description, price, language, rating))
                    genres.add(genre)
                }
            } else {
            }
        }

        private fun getBook(bookItem: JSONObject): Book {
            TODO()
        }

        private fun getComposition(composition: Int): JSONObject {
            var value = JSONObject()
            val response: JSONObject = CompositionTask().execute(composition.toString(), "", "", "", "", "").get()
            if (response.has("status") && response["status"] == "ok") {
                val result = response.getJSONArray("result")
                value = result.getJSONObject(0)
            } else {
            }
            return value
        }

        fun getBooks(): ArrayList<Book> {
            return books
        }

        fun getGenres(): MutableSet<String> {
            return genres
        }
    }
}