package com.gidsor.bookstore.ui.store

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.db.OrderArrayData
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.network.AddToLibraryTask
import com.gidsor.bookstore.data.network.DownloadImageTask
import com.gidsor.bookstore.data.network.LibraryTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.updateLibraryOfUser
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.viewAccount
import com.squareup.picasso.Picasso

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
        val imageView = view!!.findViewById<ImageView>(R.id.book_item_image)
        Picasso.get().load(book.image).placeholder(R.drawable.not_found).error(R.drawable.not_found)
                .fit().centerInside()
                .into(imageView)
        //DownloadImageTask(view!!.findViewById(R.id.book_item_image)).execute(book.image)

        view!!.findViewById<RatingBar>(R.id.book_item_rating).rating = book.rating
        view!!.findViewById<TextView>(R.id.book_item_name).text = book.name
        view!!.findViewById<TextView>(R.id.book_item_author).text = book.author
        view!!.findViewById<Button>(R.id.book_item_buy_button).text = "КУПИТЬ за " + book.price.toString() + ",00 \u20BD"
        view!!.findViewById<TextView>(R.id.book_item_genres).text = book.genre
        view!!.findViewById<TextView>(R.id.book_item_description).text = book.description
        view!!.findViewById<TextView>(R.id.book_item_publisher).text = "Издатель: " + book.publisher
        view!!.findViewById<TextView>(R.id.book_item_year).text = "Год издания: " + book.year

        view!!.findViewById<Button>(R.id.book_item_buy_button).setOnClickListener { v ->
            if (user.id != -1) {
                OrderArrayData.addOrder(Order(user.id.toString(), book))
            } else {
                Toast.makeText(context, "Войдите в учетную запись для добавления в корзину", Toast.LENGTH_SHORT).show()
            }
        }

        view!!.findViewById<Button>(R.id.book_item_menu_button).setOnClickListener { v ->
            showMenu(v, book)
        }
    }

    fun showMenu(view: View, book: Book) {
        val popupMenu = PopupMenu(activity as Context, view)
        popupMenu.inflate(R.menu.menu_book_item_button)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.book_item_button_add_to_order -> {
                    if (user.id != -1) {
                        OrderArrayData.addOrder(Order(user.id.toString(), book))
                        updateLibraryOfUser(user, viewAccount)
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для добавления в корзину", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.book_item_button_add_to_library_read -> {
                    if (user.id != -1) {
                        AddToLibraryTask().execute(user.id.toString(), "1", book.composition.toString())
                        updateLibraryOfUser(user, viewAccount)
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для доступа к библиотеке", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.book_item_button_add_to_library_deferred -> {
                    if (user.id != -1) {
                        AddToLibraryTask().execute(user.id.toString(), "2", book.composition.toString())
                        updateLibraryOfUser(user, viewAccount)
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для доступа к библиотеке", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.book_item_button_add_to_library_desired -> {
                    if (user.id != -1) {
                        AddToLibraryTask().execute(user.id.toString(), "3", book.composition.toString())
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для доступа к библиотеке", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }
}