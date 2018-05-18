package com.gidsor.bookstore.ui.genre

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Genre
import com.gidsor.bookstore.ui.main.MainActivity
import com.gidsor.bookstore.ui.store.StoreFragment

class GenreFragment : ListFragment() {

    private val genres: Array<String> = arrayOf("Психология", "Для детей", "Фантастика и фэнтези", "Компьютеры и технологии", "Романтика")
    private val icons: Array<Int> = arrayOf(R.drawable.ic_psychology_white_24dp, R.drawable.ic_child_white_24dp,
            R.drawable.ic_fantasy_white_24dp, R.drawable.ic_computer_white_24dp, R.drawable.ic_like_white_24dp)

    private val genreItems: ArrayList<Genre> = ArrayList()
    private lateinit var adapter: GenreAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_genre, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (i in 0 until genres.count()) {
            genreItems.add(Genre(genres[i], icons[i]))
        }
        adapter = GenreAdapter(view!!.context, genreItems)
        listAdapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(activity, genres[position], Toast.LENGTH_SHORT).show()
            MainActivity.loadStoreFragmentWithGenre(genres[position])
        }
    }
}