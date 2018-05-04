package com.gidsor.bookstore.ui.bookitem

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
            convertView = mInflater.inflate(R.layout.fragment_book_item, null)
        }
        val imageBook: ImageView = convertView!!.findViewById(R.id.book_image)
        val nameGenre: TextView = convertView!!.findViewById(R.id.book_name)

        val bookPos: Book = bookItems[position]
        imageBook.setImageResource(bookPos.image)
        nameGenre.text = bookPos.name

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