package com.gidsor.bookstore.ui.store

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book

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
        view!!.findViewById<ImageView>(R.id.book_item_image).setImageResource(book.image)
        //view!!.findViewById<RatingBar>(R.id.book_item_rating).numStars = 4
        view!!.findViewById<TextView>(R.id.book_item_name).text = book.name
        view!!.findViewById<TextView>(R.id.book_item_author).text = book.authors
        view!!.findViewById<Button>(R.id.book_item_buy_button).text = "КУПИТЬ за " + book.price.toString() + ",00 \u20BD"
        view!!.findViewById<TextView>(R.id.book_item_genres).text = book.genre
        view!!.findViewById<TextView>(R.id.book_item_description).text = book.description
        view!!.findViewById<TextView>(R.id.book_item_publisher).text = "Издатель: " + book.publisher
        view!!.findViewById<TextView>(R.id.book_item_year).text = "Год издания: " + book.year
    }
}