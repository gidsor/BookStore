package com.gidsor.bookstore.ui.novelties

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.ui.bookitem.BookAdapter

class NoveltiesFragment : ListFragment() {
    private val names: Array<String> = arrayOf("Тонкое искусство пофигизма", "Ходячий замок", "Оно",
            "Книга пыли. Прекрасная дикарка", "Верховная Мать Змей", "Совершенный код", "Потерянное озеро")
    private val images: Array<Int> = arrayOf(R.drawable.book1, R.drawable.book2, R.drawable.book3,
            R.drawable.book4, R.drawable.book5, R.drawable.book6, R.drawable.book7)

    private lateinit var genreItems: ArrayList<Book>
    private lateinit var adapter: BookAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_novelties, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        genreItems = ArrayList()
        for (i in 0 until names.count()) {
            genreItems.add(Book(names[i], images[i]))
        }
        adapter = BookAdapter(view!!.context, genreItems)
        listAdapter = adapter
        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(activity, names[position], Toast.LENGTH_SHORT).show()
        }
    }
}