package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Selection
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.network.RegistrationTask
import org.json.JSONObject
import android.text.Editable



class RegistrationDialog : DialogFragment() {
    lateinit var email: String
    lateinit var password: String
    lateinit var firstname: String
    lateinit var lastname: String
    lateinit var patronymic: String
    lateinit var phone: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneEditText = view.findViewById<EditText>(R.id.registration_phone_input)
        phoneEditText.setText("+7 ")
        Selection.setSelection(phoneEditText.text, phoneEditText.text.length)
        phoneEditText.addTextChangedListener(object : TextWatcher {

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable) {
                if (!s.toString().startsWith("+7 ")) {
                    phoneEditText.setText("+7")
                    Selection.setSelection(phoneEditText.text, phoneEditText.text.length)

                }

            }
        })

        view.findViewById<Button>(R.id.registration_registration).setOnClickListener {
            email = view.findViewById<EditText>(R.id.registration_email_input).text.toString()
            password = view.findViewById<EditText>(R.id.registration_password_input).text.toString()
            firstname = view.findViewById<EditText>(R.id.registration_firstname_input).text.toString()
            lastname = view.findViewById<EditText>(R.id.registration_lastname_input).text.toString()
            patronymic = view.findViewById<EditText>(R.id.registration_patronymic_input).text.toString()
            phone = phoneEditText.text.toString()


            val passwordConfirm = view.findViewById<EditText>(R.id.registration_password_input_confirm).text.toString()
            if (password == passwordConfirm && password.length >= 8) {
                if (phone.length == "+7 1112223344".length) {
                    val response: JSONObject = RegistrationTask().execute(email, password, firstname, lastname, patronymic, phone).get()
                    if (response.has("status") && response["status"] == "ok") {
                        Toast.makeText(activity, "Пользователь создан", Toast.LENGTH_SHORT).show()
                        dismiss()
                    } else {
                        Toast.makeText(activity, response.getString("msg"), Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(activity, "Введите телефон формата +7 1112223344", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(activity, "Пароли не совпадают или меньше 8 символов", Toast.LENGTH_SHORT).show()
            }
        }
    }
}