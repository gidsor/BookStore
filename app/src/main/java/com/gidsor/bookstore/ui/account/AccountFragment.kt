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
import org.w3c.dom.Text

class AccountFragment : Fragment() {

    companion object {
        var user: User = User()
        lateinit var viewAccount: View
        fun updateCurrentUser(newUser: User, view: View = viewAccount) {
            user = newUser

            view.findViewById<TextView>(R.id.account_login)?.text = user.id.toString()
            view.findViewById<TextView>(R.id.account_email)?.text = user.email
            if (user.id != -1) {
                view.findViewById<Button>(R.id.account_login_and_registration)?.visibility = View.GONE
            }
        }

        fun updateLibraryOfUser(currentUser: User, view: View = viewAccount) {
            val url = "http://212.47.240.244/api/library?id=${currentUser.id}"
            url.httpGet().responseString { request, response, result ->
                var (data, error) = result
                if (error == null) {
                    Log.i("REQUEST TESTING", data)
                    data = StringEscapeUtils.unescapeJava(data)
                    Log.i("REQUEST TESTING", data)
                    val library: LinearLayout = view.findViewById(R.id.account_library)
                    library.addView(TextView(view.context))
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
        updateLibraryOfUser(user, view)
        loginDialog = LoginDialog()

        view.findViewById<Button>(R.id.account_login_and_registration).setOnClickListener {
            loginDialog.show(fragmentManager, "loginDialog")
        }
    }
}