package com.gidsor.bookstore.ui.purchase

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.db.BookArrayData
import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.ui.main.MainActivity

class PurchaseFragment : ListFragment() {

    private lateinit var orderItems: ArrayList<Order>
    private lateinit var adapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_purchase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        orderItems = ArrayList()
        for (i in 0 until 6) {
            orderItems.add(Order("1", BookArrayData.getBooks()[i]))
        }
        adapter = OrderAdapter(view!!.context, orderItems)
        listAdapter = adapter
//        // Show all books or books of selected genre
//        bookItems = ArrayList()
//        if (genreToShow != "Все") {
//            for (book in BookArrayData.getBooks()) {
//                if (book.genre == genreToShow) {
//                    bookItems.add(book)
//                }
//            }
//        } else {
//            bookItems = BookArrayData.getBooks()
//        }
//
//        (activity as MainActivity).bottomNavigationView.menu.getItem(0).isChecked = true
//        adapter = BookAdapter(view!!.context, bookItems)
//        listAdapter = adapter
//        listView.setOnItemClickListener { parent, view, position, id ->
//            MainActivity.loadBookItemFragment(bookItems[position])
//        }
    }
}