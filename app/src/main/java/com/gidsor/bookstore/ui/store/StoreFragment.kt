package com.gidsor.bookstore.ui.store

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.db.BookTestArrayData
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.ui.main.MainActivity

class StoreFragment : ListFragment() {

    private lateinit var bookItems: ArrayList<Book>
    private lateinit var adapter: BookAdapter
    var genreToShow: String = "Все"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_store, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Show all books or books of selected genre
        bookItems = ArrayList()
        if (genreToShow != "Все") {
            for (book in BookTestArrayData.books) {
                if (book.genre == genreToShow) {
                    bookItems.add(book)
                }
            }
        } else {
            bookItems = BookTestArrayData.books
        }

        (activity as MainActivity).bottomNavigationView.menu.getItem(0).isChecked = true
        adapter = BookAdapter(view!!.context, bookItems)
        listAdapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            // Toast.makeText(activity, bookItems[position].name, Toast.LENGTH_SHORT).show()
            // Load full detail about book
            MainActivity.loadBookItemFragment(bookItems[position])
        }
    }
}