package com.gidsor.bookstore.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BasketArrayData
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.ui.main.MainActivity
import com.squareup.picasso.Picasso

class BasketAdapter(val context: Context) : BaseAdapter() {
    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = _convertView
        if (convertView == null) {
            val mInflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = mInflater.inflate(R.layout.fragment_basket_item, null)!!
        }

        val imageBook: ImageView = convertView.findViewById(R.id.order_book_image)
        val nameBook: TextView = convertView.findViewById(R.id.order_book_name)
        val authorBook: TextView = convertView.findViewById(R.id.order_book_author)
        val priceBook: TextView = convertView.findViewById(R.id.order_book_price)
        val removeButton: Button = convertView.findViewById(R.id.order_book_remove_button)
        val countBook: Button = convertView.findViewById(R.id.order_book_count)

        val baskedItem = BasketArrayData.getBasket()[position]
        val book: Book = baskedItem.book

        Picasso.get().load(book.envelope).placeholder(R.drawable.not_found).error(R.drawable.not_found)
                .fit().centerInside()
                .into(imageBook)

        nameBook.text = book.title
        authorBook.text = book.author
        priceBook.text = book.price.toString() + ",00 \u20BD"
        countBook.text = baskedItem.count.toString()

        removeButton.setOnClickListener { v ->
            BasketArrayData.removeFromBasket(BasketArrayData.getBasket()[position])
            (context as MainActivity).badge.text = BasketArrayData.countOfBooks().toString()
            notifyDataSetChanged()
        }

        return convertView
    }

    override fun getItem(position: Int): Any {
        return BasketArrayData.getBasket()[position]
    }

    override fun getItemId(position: Int): Long {
        return BasketArrayData.getBasket().indexOf(getItem(position)).toLong()
    }

    override fun getCount(): Int {
        return BasketArrayData.getBasket().count()
    }
}