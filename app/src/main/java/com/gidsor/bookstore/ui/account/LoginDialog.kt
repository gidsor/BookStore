package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.HTTPRequestAPI
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.getAs
import org.json.JSONObject
import java.net.URL
import javax.xml.transform.Result

class LoginDialog : DialogFragment() {

    lateinit var email: String
    lateinit var password: String
    lateinit var registrationDialog: DialogFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationDialog = RegistrationDialog()

        view.findViewById<Button>(R.id.login_login).setOnClickListener {
            email = view.findViewById<EditText>(R.id.login_email_input).text.toString()
            password = view.findViewById<EditText>(R.id.login_password_input).text.toString()
            val url = "http://212.47.240.244/api/login?login=$email&password=$password"
            url.httpGet().responseObject(User.Deserializer()) { req, res, result ->
                //result is of type Result<User, Exception>
                val (user, err) = result
                if (err != null) {
                    Log.i("LOGIN REQUEST ERROR", err.toString())
                }
                if (user != null) {
                    Log.i("TESTING REQUEST", user.id.toString())
                    // Закрывает окно входа
                    if (user.id != -1) {
                        val newUser: User = User(user.id, email)
                        AccountFragment.updateCurrentUser(newUser)
//                        AccountFragment.updateLibraryOfUser(newUser)
                        dismiss()
                    }
                }
            }
        }

        view.findViewById<Button>(R.id.login_registration).setOnClickListener {
            registrationDialog.show(fragmentManager, "registrationDialog")
        }
    }
}