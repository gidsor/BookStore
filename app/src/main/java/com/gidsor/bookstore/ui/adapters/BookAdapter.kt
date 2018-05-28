package com.gidsor.bookstore.ui.adapters

import android.app.Activity
import android.content.Context
import android.support.v7.widget.PopupMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.squareup.picasso.Picasso
import com.gidsor.bookstore.data.database.BasketArrayData
import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.data.network.AddToLibraryTask
import com.gidsor.bookstore.ui.account.AccountFragment
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user


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

        Picasso.get().load(book.image).placeholder(R.drawable.not_found).error(R.drawable.not_found)
                .fit().centerInside()
                .into(imageBook)
        //DownloadImageTask(imageBook).execute(book.image)

        ratingBook.rating = book.rating
        nameBook.text = book.name
        authorBook.text = book.author
        buyButtonBook.text = book.price.toString() + ",00 \u20BD"

        buyButtonBook.setOnClickListener { v ->
            if (user.id != -1) {
                BasketArrayData.addToBasket(Order(user.id.toString(), book))
            } else {
                Toast.makeText(context, "Войдите в учетную запись для добавления в корзину", Toast.LENGTH_SHORT).show()
            }
        }

        menuButtonBook.setOnClickListener { v ->
            showMenu(v, book)
        }
        return convertView
    }

    fun showMenu(view: View, book: Book) {
        val popupMenu = PopupMenu(context, view)
        popupMenu.inflate(R.menu.menu_book_item_button)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.book_item_button_add_to_order -> {
                    if (user.id != -1) {
                        BasketArrayData.addToBasket(Order(user.id.toString(), book))
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для добавления в корзину", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.book_item_button_add_to_library_read -> {
                    if (user.id != -1) {
                        AddToLibraryTask().execute(user.id.toString(), "1", book.composition.toString())
                        AccountFragment.updateLibraryOfUser(user, AccountFragment.viewAccount)
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для доступа к библиотеке", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.book_item_button_add_to_library_deferred -> {
                    if (user.id != -1) {
                        AddToLibraryTask().execute(user.id.toString(), "2", book.composition.toString())
                        AccountFragment.updateLibraryOfUser(user, AccountFragment.viewAccount)
                    } else {
                        Toast.makeText(context, "Войдите в учетную запись для доступа к библиотеке", Toast.LENGTH_SHORT).show()
                    }
                    true
                }
                R.id.book_item_button_add_to_library_desired -> {
                    if (user.id != -1) {
                        AddToLibraryTask().execute(user.id.toString(), "3", book.composition.toString())
                        AccountFragment.updateLibraryOfUser(user, AccountFragment.viewAccount)
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