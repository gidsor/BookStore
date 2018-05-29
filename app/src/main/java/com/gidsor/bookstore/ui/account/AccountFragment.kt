package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.LibraryArrayData
import com.gidsor.bookstore.data.database.BasketArrayData
import com.gidsor.bookstore.data.database.OrderOfUserArrayData
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.DelFromLibraryTask

class AccountFragment : Fragment() {

    companion object {
        var user: User = User()
        lateinit var viewAccount: View
        fun updateCurrentUser(newUser: User, view: View) {
            user = newUser

            view.findViewById<TextView>(R.id.account_email)?.text = user.email
            view.findViewById<TextView>(R.id.account_real_name)?.text = user.realName
            view.findViewById<TextView>(R.id.account_phone)?.text = user.phone

            updateLibraryOfUser(user, view)
            updateOrdersOfUser(user, view)

            if (user.id == -1) {
                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_exit)?.visibility = View.INVISIBLE
            } else {
                view.findViewById<Button>(R.id.account_exit)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.INVISIBLE
            }
        }

        fun updateLibraryOfUser(currentUser: User, view: View) {
            val library: LinearLayout = view.findViewById(R.id.account_library)
            library.removeAllViewsInLayout()
            LibraryArrayData.updateCompositions(currentUser)
            for (i in LibraryArrayData.getCompositions()) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView

                val bookName = i.title
                val bookAuthor = i.author

                val removeButton = Button(view.context)
                val paramsOfButton = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfButton.setMargins(10, 10, 0, 0)
                removeButton.layoutParams = paramsOfTextView
                removeButton.text = "Удалить из библиотеки"
                removeButton.setOnClickListener { v ->
                    DelFromLibraryTask().execute(user.id.toString(), i.lib, i.composition)
                    updateLibraryOfUser(user, view)
                }
                when {
                    i.lib == "1" -> {
                        newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: Прочитанное"
                    }
                    i.lib == "2" -> {
                        newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: Отложенное"
                    }
                    i.lib == "3" -> {
                        newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: Избранное"
                    }
                }
                library.addView(newTextView)
                library.addView(removeButton)
            }
            if (LibraryArrayData.getCompositions().size == 0) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView
                newTextView.text = "Пусто"
                library.addView(newTextView)
            }
        }

        fun updateOrdersOfUser(currentUser: User, view: View) {
            val myOrders: LinearLayout = view.findViewById(R.id.account_orders)
            myOrders.removeAllViewsInLayout()
            OrderOfUserArrayData.updateOrdersOfUser(currentUser)
            for (i in OrderOfUserArrayData.getOrdersOfUser().reversed()) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView

                newTextView.text = """Дата заказа: ${i.dateOrder}
Цена заказа: ${i.price}
Дата доставки: ${i.dateDelivery}
Статус доставки: ${i.statusDelivery}
Вариант доставки: ${i.descriptionDelivery}
Цена доставки: ${i.priceDelivery}
Комментарий: ${i.comment}
Статус оплаты: ${i.descriptionPrice}"""

                myOrders.addView(newTextView)
            }
            if (OrderOfUserArrayData.getOrdersOfUser().size == 0) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView
                newTextView.text = "Пусто"
                myOrders.addView(newTextView)
            }
        }
    }

    lateinit var loginDialog: DialogFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAccount = view
        updateCurrentUser(user, view)
        loginDialog = LoginDialog()

        view.findViewById<Button>(R.id.account_login_and_registration).setOnClickListener {
            loginDialog.show(fragmentManager, "loginDialog")
        }

        view.findViewById<Button>(R.id.account_exit).setOnClickListener {
            updateCurrentUser(User(), view)
        }
    }
}