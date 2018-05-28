package com.gidsor.bookstore.ui.store

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BookArrayData
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.ui.main.MainActivity

class StoreFragment : ListFragment() {

    private lateinit var bookItems: ArrayList<Book>
    private lateinit var adapter: BookAdapter
    var genreToShow: String = "Все"
    var searchTitile: String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Show all books or books of selected genre
        bookItems = ArrayList()
        if (genreToShow != "Все") {
            for (book in BookArrayData.getBooks()) {
                if (book.genre == genreToShow) {
                    bookItems.add(book)
                }
            }
        } else {
            bookItems = BookArrayData.getBooks()
        }

        var bookItemsToShow = arrayListOf<Book>()

        if (searchTitile != "") {
            for (book in bookItems) {
                if (book.name.toLowerCase().contains(searchTitile.toLowerCase())) {
                    bookItemsToShow.add(book)
                }
            }
        } else {
            bookItemsToShow = bookItems
        }

        (activity as MainActivity).bottomNavigationView.menu.getItem(0).isChecked = true
        adapter = BookAdapter(view!!.context, bookItemsToShow)
        listAdapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            MainActivity.loadBookItemFragment(bookItemsToShow[position])
        }
    }
}