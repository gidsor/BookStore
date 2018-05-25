package com.gidsor.bookstore.ui.purchase

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
import com.gidsor.bookstore.data.model.Order
import com.squareup.picasso.Picasso

class OrderAdapter(val context: Context, val ordersItems: ArrayList<Order>) : BaseAdapter() {
    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = _convertView
        if (convertView == null) {
            val mInflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = mInflater.inflate(R.layout.fragment_purchase_order_item, null)!!
        }

        val imageBook: ImageView = convertView.findViewById(R.id.order_book_image)
        val nameBook: TextView = convertView.findViewById(R.id.order_book_name)
        val authorBook: TextView = convertView.findViewById(R.id.order_book_author)
        val price: TextView = convertView.findViewById(R.id.order_book_price)

        val book: Book = ordersItems[position].book

        Picasso.get().load(book.image).placeholder(R.drawable.not_found).error(R.drawable.not_found)
                .fit().centerInside()
                .into(imageBook)
        //DownloadImageTask(imageBook).execute(book.image)

        nameBook.text = book.name
        authorBook.text = book.author
        price.text = book.price.toString() + ",00 \u20BD"


        return convertView
    }

    override fun getItem(position: Int): Any {
        return ordersItems[position]
    }

    override fun getItemId(position: Int): Long {
        return ordersItems.indexOf(getItem(position)).toLong()
    }

    override fun getCount(): Int {
        return ordersItems.count()
    }
}