package com.gidsor.bookstore.ui.store

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.DownloadImageTask

class BookItemFragment : Fragment() {
    lateinit var bookToShow: Book
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBook(bookToShow)
    }

    private fun setBook(book: Book) {
        DownloadImageTask(view!!.findViewById(R.id.book_item_image)).execute(book.image)

        view!!.findViewById<RatingBar>(R.id.book_item_rating).rating = book.rating
        view!!.findViewById<TextView>(R.id.book_item_name).text = book.name
        view!!.findViewById<TextView>(R.id.book_item_author).text = book.author
        view!!.findViewById<Button>(R.id.book_item_buy_button).text = "КУПИТЬ за " + book.price.toString() + ",00 \u20BD"
        view!!.findViewById<TextView>(R.id.book_item_genres).text = book.genre
        view!!.findViewById<TextView>(R.id.book_item_description).text = book.description
        view!!.findViewById<TextView>(R.id.book_item_publisher).text = "Издатель: " + book.publisher
        view!!.findViewById<TextView>(R.id.book_item_year).text = "Год издания: " + book.year

        view!!.findViewById<Button>(R.id.book_item_menu_button).setOnClickListener {v ->
            showMenu(v, book)
        }
    }

    fun showMenu(view: View, book: Book) {
        val popupMenu = PopupMenu(activity as Context, view)
        popupMenu.inflate(R.menu.menu_book_item_button)
        popupMenu.show()
    }
}