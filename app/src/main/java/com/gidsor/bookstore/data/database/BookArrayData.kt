package com.gidsor.bookstore.data.database

import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.BookTask
import com.gidsor.bookstore.data.network.CompositionTask
import org.json.JSONObject

object BookArrayData {
    private var books: ArrayList<Book> = arrayListOf()
    private var genres: MutableSet<String> = mutableSetOf()

    fun updateBooks(): Boolean {
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

                var rating = 0f
                if (!bookItem.isNull("mark")) {
                    rating = bookItem.getString("mark").toFloat()
                }

                val composition = bookItem.getInt("composition")
                val compositionJSON = getComposition(composition)

                val author = compositionJSON.getString("author")
                val genre = compositionJSON.getString("genre")
                val name = compositionJSON.getString("title")


                books.add(Book(isbn, composition, name, image, genre, author, year, publisher, description, price, language, rating))
                genres.add(genre)
            }
        }
        return true
    }

    private fun getComposition(composition: Int): JSONObject {
        val response: JSONObject = CompositionTask().execute(composition.toString(), "", "", "", "", "").get()
        return response.getJSONArray("result").getJSONObject(0)
    }

    fun updateRating(book: Book) {
        val response: JSONObject = BookTask().execute(book.isbn, "").get()
        if (response.has("status") && response["status"] == "ok") {
            val bookItem = response.getJSONArray("result").getJSONObject(0)
            var rating = 0f
            if (!bookItem.isNull("mark")) {
                rating = bookItem.getString("mark").toFloat()
            }
            book.rating = rating
        }
    }

    fun getBook(isbn: String): Book {
        for (book in books) {
            if (book.isbn == isbn) {
                return book
            }
        }
        return books[0]
    }
    fun getBooks(): ArrayList<Book> {
        return books
    }

    fun getGenres(): MutableSet<String> {
        return genres
    }
}