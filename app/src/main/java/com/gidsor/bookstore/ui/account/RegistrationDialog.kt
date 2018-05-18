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
import com.github.kittinunf.fuel.httpGet

class RegistrationDialog : DialogFragment() {
    lateinit var email: String
    lateinit var password: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.registration_registration).setOnClickListener {
            email = view.findViewById<EditText>(R.id.registration_email_input).text.toString()
            password = view.findViewById<EditText>(R.id.registration_password_input).text.toString()
            val url = "http://212.47.240.244/api/registration?login=$email&password=$password"
            url.httpGet().responseString { request, response, result ->
                val (data, error) = result
                if (error == null) {
                    Log.i("REQUEST TESTING", data)
                    dismiss()
                } else {
                    Log.i("REGISTRATION ERROR", error.toString())
                }
            }
        }
    }
}