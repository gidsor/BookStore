package com.gidsor.bookstore.data.database

import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.model.Composition
import com.gidsor.bookstore.data.network.BookTask
import com.gidsor.bookstore.data.network.CompositionTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

object BookArrayData {
    private var books: ArrayList<Book> = arrayListOf()
    private var genres: MutableSet<String> = mutableSetOf()
    private var compositions: ArrayList<Composition> = arrayListOf()

    fun updateBooks(): Boolean {
        val responseBook: JSONObject = BookTask().execute("", "").get()
        if (responseBook.has("status") && responseBook["status"] == "ok") {
            val booksType = object  : TypeToken<ArrayList<Book>>() {}.type
            books = Gson().fromJson<ArrayList<Book>>(responseBook.getJSONArray("result").toString(), booksType)

            val responseComposition: JSONObject = CompositionTask().execute("", "", "", "", "", "").get()
            if (responseComposition.has("status") && responseComposition["status"] == "ok") {
                val compositionsType = object : TypeToken<ArrayList<Composition>>() {}.type
                compositions = Gson().fromJson<ArrayList<Composition>>(responseComposition.getJSONArray("result").toString(), compositionsType)
            }

            for (b in books) {
                for (c in compositions) {
                    if (b.composition.toString() == c.composition) {
                        b.author = c.author
                        b.genre = c.genre
                        b.image = "http://212.47.240.244/images/${b.image}"
                        genres.add(b.genre)
                        break
                    }
                }
            }
        }
        return true
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

    fun getCompositions(): ArrayList<Composition> {
        return compositions
    }

    fun getGenres(): MutableSet<String> {
        return genres
    }
}