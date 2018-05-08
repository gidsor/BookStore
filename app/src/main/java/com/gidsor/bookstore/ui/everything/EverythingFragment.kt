package com.gidsor.bookstore.ui.everything

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.ui.bookitem.BookAdapter
import com.gidsor.bookstore.ui.bookitem.BookTestArrayData

class EverythingFragment : ListFragment() {

    private lateinit var bookItems: ArrayList<Book>
    private lateinit var adapter: BookAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_everything, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookItems = BookTestArrayData.books
//        bookItems = ArrayList()
//        for (i in 0 until names.count()) {
//            bookItems.add(Book(names[i], images[i]))
//        }
        adapter = BookAdapter(view!!.context, bookItems)
        listAdapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(activity, bookItems[position].name, Toast.LENGTH_SHORT).show()
        }
    }
}