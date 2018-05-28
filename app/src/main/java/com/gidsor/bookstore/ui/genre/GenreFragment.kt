package com.gidsor.bookstore.ui.genre

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BookArrayData
import com.gidsor.bookstore.ui.main.MainActivity

class GenreFragment : ListFragment() {
    private lateinit var adapter: GenreAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = GenreAdapter(view!!.context, BookArrayData.getGenres())
        listAdapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            MainActivity.loadStoreFragmentWithGenreAndSearch(BookArrayData.getGenres().elementAt(position))
        }
    }
}