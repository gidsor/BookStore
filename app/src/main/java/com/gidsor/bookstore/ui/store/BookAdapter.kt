package com.gidsor.bookstore.ui.store

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book

class BookAdapter(val context: Context, val bookItems: ArrayList<Book>) : BaseAdapter() {

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = _convertView
        if (convertView == null) {
            val mInflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = mInflater.inflate(R.layout.fragment_store_book_item, null)!!
        }

        val imageBook: ImageView = convertView.findViewById(R.id.book_image)
        val nameBook: TextView = convertView.findViewById(R.id.book_name)
        val authorsBook: TextView = convertView.findViewById(R.id.book_authors)
        val yearBook: TextView = convertView.findViewById(R.id.book_year)
        val priceBook: TextView = convertView.findViewById(R.id.book_price)
        val genreBook: TextView = convertView.findViewById(R.id.book_genre)
        val descriptionBook: TextView = convertView.findViewById(R.id.book_description)

        val book: Book = bookItems[position]
        imageBook.setImageResource(book.image)
        nameBook.text = book.name
        authorsBook.text = "Автор: " + book.authors
        yearBook.text = "Год издания: " + book.year.toString()
        priceBook.text = "Цена: " + book.price.toString() + " \u20BD"
        genreBook.text = "Жанр: " + book.genre
        descriptionBook.text = book.description

        return convertView
    }

    override fun getItem(position: Int): Any {
        return bookItems[position]
    }

    override fun getItemId(position: Int): Long {
        return bookItems.indexOf(getItem(position)).toLong()
    }

    override fun getCount(): Int {
        return bookItems.count()
    }
}