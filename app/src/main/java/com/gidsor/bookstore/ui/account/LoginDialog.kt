package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.LoginTask
import com.gidsor.bookstore.data.network.UserTask
import com.gidsor.bookstore.ui.main.MainActivity
import org.json.JSONObject

class LoginDialog() : DialogFragment() {

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
            val response: JSONObject = LoginTask().execute(email, password).get()
            if (response.has("status") && response["status"] == "ok") {
                var userInfo = UserTask().execute(response.getJSONObject("result").getString("id")).get()
                var r = userInfo.getJSONObject("result")
                var id = r.getInt("id")
                var name = r.getString("lastname") + " " + r.getString("firstname") + " " + r.getString("patronymic")
                var phone = r.getString("phone")
                val user: User = User(id, email, name, phone)
                AccountFragment.updateCurrentUser(user, AccountFragment.viewAccount)
                Toast.makeText(activity, "Вход выполнен", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(activity, "Ошибка!!!", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.login_registration).setOnClickListener {
            registrationDialog.show(fragmentManager, "registrationDialog")
        }
    }
}