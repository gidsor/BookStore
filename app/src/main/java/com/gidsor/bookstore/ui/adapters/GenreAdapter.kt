package com.gidsor.bookstore.ui.adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.gidsor.bookstore.R

class GenreAdapter(val context: Context, val genreItems: MutableSet<String>) : BaseAdapter() {
    override fun getView(position: Int, _convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = _convertView
        if (convertView == null) {
            val mInflater: LayoutInflater = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = mInflater.inflate(R.layout.fragment_genre_item, null)
        }

        val nameGenre: TextView = convertView!!.findViewById(R.id.genre_name)

        val genrePos: String = genreItems.elementAt(position)
        nameGenre.text = genrePos

        return convertView
    }

    override fun getItem(position: Int): Any {
        return genreItems.elementAt(position)
    }

    override fun getItemId(position: Int): Long {
        return genreItems.indexOf(getItem(position)).toLong()
    }

    override fun getCount(): Int {
        return genreItems.count()
    }
}