package com.gidsor.bookstore.ui.store

import android.app.Activity
import android.content.Context
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.DownloadImageTask

class BookAdapter(val context: Context, val bookItems: ArrayList<Book>) : BaseAdapter() {

    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = _convertView
        if (convertView == null) {
            val mInflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = mInflater.inflate(R.layout.fragment_store_book_item, null)!!
        }

        val imageBook: ImageView = convertView.findViewById(R.id.book_image)
        val ratingBook: RatingBar = convertView.findViewById(R.id.book_rating)
        val nameBook: TextView = convertView.findViewById(R.id.book_name)
        val authorBook: TextView = convertView.findViewById(R.id.book_author)
        val buyButtonBook: Button = convertView.findViewById(R.id.book_buy_button)
        val menuButtonBook: Button = convertView.findViewById(R.id.book_menu_button)

        val book: Book = bookItems[position]

        DownloadImageTask(imageBook).execute(book.image)

        ratingBook.rating = book.rating
        nameBook.text = book.name
        authorBook.text = book.author
        buyButtonBook.text = book.price.toString() + ",00 \u20BD"
        // TODO make add book to order by click buyButton

        menuButtonBook.setOnClickListener { v ->
            showMenu(v)
        }
        return convertView
    }

    fun showMenu(view: View) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.menu_book_item_button)
        popupMenu.show()
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