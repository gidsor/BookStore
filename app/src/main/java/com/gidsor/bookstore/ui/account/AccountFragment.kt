package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.db.LibraryArrayData
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.LibraryTask
import com.github.kittinunf.fuel.httpGet
import org.apache.commons.lang.StringEscapeUtils
import org.json.JSONArray
import org.json.JSONObject

class AccountFragment : Fragment() {

    companion object {
        var user: User = User()
        lateinit var viewAccount: View
        fun updateCurrentUser(newUser: User, view: View) {
            user = newUser

            view.findViewById<TextView>(R.id.account_email)?.text = user.email
            view.findViewById<TextView>(R.id.account_real_name)?.text = user.realName

            updateLibraryOfUser(user, view)

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
            val userID: String = currentUser.id.toString()
            LibraryArrayData.updateCompositions(currentUser)
            for (i in LibraryArrayData.getCompositions()) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView
                val bookName = i.title
                val bookAuthor = i.author
                when {
                    i.lib == "1" -> {
                        newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: Прочитанное"
                        library.addView(newTextView)
                    }
                    i.lib == "2" -> {
                        newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: Отложенное"
                        library.addView(newTextView)
                    }
                    i.lib == "3" -> {
                        newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: Избранное"
                        library.addView(newTextView)
                    }
                }
            }
            if (LibraryArrayData.getCompositions().size == 0) {
                val newTextView = TextView(view.context)
                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                paramsOfTextView.setMargins(10, 10, 0, 0)
                newTextView.layoutParams = paramsOfTextView
                newTextView.text = "Пусто"
                library.addView(newTextView)
            }
//            val response: JSONObject = LibraryTask().execute(userID, "1").get()
//            if (response.has("status") && response["status"] == "ok" && userID != "-1" && !response.isNull("result")) {
//
//                val dataLibrary: JSONArray = response.getJSONArray("result")
//
//                for (i in 0 until dataLibrary.length()) {
//                    val newTextView = TextView(view.context)
//                    val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                    paramsOfTextView.setMargins(10, 10, 0, 0)
//                    newTextView.layoutParams = paramsOfTextView
//                    val libraryDataItem = dataLibrary.getJSONObject(i)
//                    val bookName = libraryDataItem.getString("book")
//                    val bookAuthor = libraryDataItem.getString("author")
//                    val bookStatus = libraryDataItem.getString("status")
//                    newTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: $bookStatus"
//                    library.addView(newTextView)
//                }
//            } else {
//                val newTextView = TextView(view.context)
//                val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                paramsOfTextView.setMargins(10, 10, 0, 0)
//                newTextView.layoutParams = paramsOfTextView
//                newTextView.text = "Пусто"
//                library.addView(newTextView)
//            }
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