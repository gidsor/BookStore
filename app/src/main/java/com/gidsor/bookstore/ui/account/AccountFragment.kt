package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.User
import com.github.kittinunf.fuel.httpGet
import org.apache.commons.lang.StringEscapeUtils
import org.json.JSONArray
import org.json.JSONObject
import org.w3c.dom.Text

class AccountFragment : Fragment() {

    companion object {
        var user: User = User()
        lateinit var viewAccount: View
        fun updateCurrentUser(newUser: User, view: View = viewAccount) {
            user = newUser

            view.findViewById<TextView>(R.id.account_login)?.text = user.id.toString()
            view.findViewById<TextView>(R.id.account_email)?.text = user.email

            updateLibraryOfUser(user, view)

            if (user.id == -1) {
                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_exit)?.visibility = View.INVISIBLE
            } else {
                view.findViewById<Button>(R.id.account_exit)?.visibility = View.VISIBLE
                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.INVISIBLE
            }
        }

        fun updateLibraryOfUser(currentUser: User, view: View = viewAccount) {
            val library: LinearLayout = view.findViewById(R.id.account_library)
            library.removeAllViewsInLayout()
            val url = "http://212.47.240.244/api/library?id=${currentUser.id}"
            url.httpGet().responseString { request, response, result ->
                var (data, error) = result
                if (error == null) {
                    Log.i("REQUEST TESTING", data)
                    data = StringEscapeUtils.unescapeJava(data)
                    Log.i("REQUEST TESTING", data)

                    val dataJSON: JSONObject = JSONObject(data)
                    val dataLibraryArrayJSON: JSONArray = dataJSON.getJSONArray("result")
                    Log.i("REQUEST TESTING", dataLibraryArrayJSON.toString())

                    for (i in 0 until dataLibraryArrayJSON.length()) {
                        val libraryBookTextView = TextView(view.context)
                        val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                        paramsOfTextView.setMargins(10, 10, 0, 0)
                        libraryBookTextView.layoutParams = paramsOfTextView
                        val libraryDataItem = dataLibraryArrayJSON.getJSONObject(i)
                        val bookName = libraryDataItem.getString("book")
                        val bookAuthor = libraryDataItem.getString("author")
                        val bookStatus = libraryDataItem.getString("status")
                        libraryBookTextView.text = "Название: $bookName\nАвтор: $bookAuthor\nСтатус: $bookStatus"
                        library.addView(libraryBookTextView)
                    }
                } else {
                    Log.i("REQUEST ERROR", error.toString())
                }
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
            updateCurrentUser(User())
        }
    }
}