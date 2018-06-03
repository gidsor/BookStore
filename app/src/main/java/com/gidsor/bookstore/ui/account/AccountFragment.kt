package com.gidsor.bookstore.ui.account

import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BasketArrayData
import com.gidsor.bookstore.data.database.LibraryArrayData
import com.gidsor.bookstore.data.database.BookArrayData
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.DelFromLibraryTask
import com.gidsor.bookstore.data.network.LoginTask
import com.gidsor.bookstore.data.network.UserTask
import com.gidsor.bookstore.ui.main.MainActivity
import com.gidsor.bookstore.utils.AppConstants
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.json.JSONObject

class AccountFragment : Fragment() {

    companion object {
        var user: User = User()
        lateinit var viewAccount: View
        private lateinit var loginDialog: DialogFragment
        private lateinit var myOrdersDialog: DialogFragment

        fun updateCurrentUser(newUser: User, view: View) {
            user = newUser

            view.findViewById<TextView>(R.id.account_email)?.text = user.email
            view.findViewById<TextView>(R.id.account_real_name)?.text = user.realName
            view.findViewById<TextView>(R.id.account_phone)?.text = "+" + user.phone

            updateLibraryOfUser(user, view)
            BasketArrayData.updateOrder(user)

            if (user.id == -1) {
                view.findViewById<TextView>(R.id.account_phone)?.text = ""

                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_show_orders)?.visibility = View.INVISIBLE
                view.findViewById<Button>(R.id.account_exit)?.visibility = View.INVISIBLE
            } else {
                view.findViewById<Button>(R.id.account_exit)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_show_orders)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.INVISIBLE
            }
        }

        fun updateLibraryOfUser(currentUser: User, view: View) {
            val libraryLayout = view.findViewById<LinearLayout>(R.id.account_library)
            libraryLayout.removeAllViewsInLayout()

            LibraryArrayData.updateLibrary(currentUser)

            val inflater = LayoutInflater.from(view.context)

            for (i in LibraryArrayData.getLibrary()) {
                val bookName = i.title
                val bookAuthor = i.author
                var bookStatus = ""
                when {
                    i.lib == "1" -> {
                        bookStatus = "Прочитанное"
                    }
                    i.lib == "2" -> {
                        bookStatus = "Отложенное"
                    }
                    i.lib == "3" -> {
                        bookStatus = "Избранное"
                    }
                }

                val libraryItemLayout = inflater.inflate(R.layout.fragment_library, null, false)
                val imageView = libraryItemLayout.findViewById<ImageView>(R.id.library_image)
                var urlImageView  = ""
                for (book in BookArrayData.getBooks()) {
                    if (book.composition.toString() == i.composition) {
                        urlImageView = book.image
                        break
                    }
                }
                Picasso.get().load(urlImageView).placeholder(R.drawable.not_found).error(R.drawable.not_found)
                        .fit().centerInside()
                        .into(imageView)

                libraryItemLayout.findViewById<TextView>(R.id.library_title).text = bookName
                libraryItemLayout.findViewById<TextView>(R.id.library_author).text = bookAuthor
                libraryItemLayout.findViewById<TextView>(R.id.library_status).text = bookStatus

                libraryItemLayout.findViewById<Button>(R.id.library_remove).setOnClickListener { v ->
                    DelFromLibraryTask().execute(user.id.toString(), i.lib, i.composition)
                    updateLibraryOfUser(user, view)
                }

                libraryLayout.addView(libraryItemLayout)
            }
            if (LibraryArrayData.getLibrary().size == 0) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView
                newTextView.text = "Пусто"
                libraryLayout.addView(newTextView)
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAccount = view
        updateCurrentUser(user, view)

        view.findViewById<Button>(R.id.account_login_and_registration).setOnClickListener {
            loginDialog = LoginDialog()
            loginDialog.show(childFragmentManager, "loginDialog")
        }

        val preferences = context!!.getSharedPreferences(AppConstants.STORAGE_ACCOUNT, Context.MODE_PRIVATE)

        if (user.id == -1) {
            val email = preferences.getString(AppConstants.TAG_EMAIL, "")
            val password = preferences.getString(AppConstants.TAG_PASSWORD, "")
            if (email != "" && password != "") {
                loginToAccount(email, password)
            }
        }

        view.findViewById<Button>(R.id.account_exit).setOnClickListener {
            val preferencesEditor = preferences.edit()
            preferencesEditor.putString(AppConstants.TAG_EMAIL, "")
            preferencesEditor.putString(AppConstants.TAG_PASSWORD, "")
            preferencesEditor.apply()
            (activity as MainActivity).badge.text = "0"
            updateCurrentUser(User(), view)
        }

        view.findViewById<Button>(R.id.account_show_orders).setOnClickListener {
            myOrdersDialog = MyOrdersDialog()
            myOrdersDialog.show(childFragmentManager, "myOrdersDialog")
        }
    }

    fun loginToAccount(email: String, password: String): Boolean {
        val response: JSONObject = LoginTask().execute(email, password).get()
        if (response.has("status") && response["status"] == "ok") {
            val userInfo = UserTask().execute(response.getJSONObject("result").getString("id")).get()

            val newUser: User = Gson().fromJson(userInfo.getJSONObject("result").toString(), User::class.java)
            newUser.realName = "${newUser.lastName} ${newUser.firstName} ${newUser.middleName}"

            AccountFragment.updateCurrentUser(newUser, AccountFragment.viewAccount)
            Toast.makeText(context, "Вход выполнен", Toast.LENGTH_SHORT).show()

            (activity as MainActivity).badge.text = BasketArrayData.countOfBooks().toString()

            val preferencesEditor = context!!.getSharedPreferences(AppConstants.STORAGE_ACCOUNT, Context.MODE_PRIVATE).edit()
            preferencesEditor.putString(AppConstants.TAG_EMAIL, email)
            preferencesEditor.putString(AppConstants.TAG_PASSWORD, password)
            preferencesEditor.apply()
            return true
        } else {
            Toast.makeText(activity, "Ошибка!!!", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}