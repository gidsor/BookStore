package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.gidsor.bookstore.R

class AccountFragment : Fragment() {

    lateinit var loginDialog: DialogFragment
    lateinit var registrationDialog: DialogFragment

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginDialog = LoginDialog()
        registrationDialog = RegistrationDialog()
        view.findViewById<Button>(R.id.account_login_and_registration).setOnClickListener {
            loginDialog.show(fragmentManager, "loginDialog")
        }
    }
}