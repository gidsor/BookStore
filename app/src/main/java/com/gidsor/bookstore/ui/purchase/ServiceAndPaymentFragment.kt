package com.gidsor.bookstore.ui.purchase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.db.BookArrayData
import com.gidsor.bookstore.data.db.OrderArrayData
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.CreateOrderTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user
import com.gidsor.bookstore.ui.main.MainActivity

class ServiceAndPaymentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_service_and_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var makeOrder: Button = view!!.findViewById(R.id.s_and_p_make_order_button)
        makeOrder.setOnClickListener {v ->
            if (user.id != -1) {
                var price = OrderArrayData.getCommonPrice()
                var rg = view!!.findViewById<RadioGroup>(R.id.s_and_p_radio_group)
                var rb = view!!.findViewById<RadioButton>(rg.checkedRadioButtonId)
                var typeOfService = rg.indexOfChild(rb) + 1

                var card = view!!.findViewById<EditText>(R.id.s_and_p_card).text.toString()
                var address = view!!.findViewById<EditText>(R.id.s_and_p_address).text.toString()
                var phone = view!!.findViewById<EditText>(R.id.s_and_p_phone).text.toString()
                var message = view!!.findViewById<EditText>(R.id.s_and_p_message).text.toString()
                CreateOrderTask().execute(user.id.toString(), card, address, phone, message, typeOfService.toString())
                OrderArrayData.updateOrders()
                MainActivity.loadStoreFragmentWithGenreAndSearch("Все")
            }
        }
    }
}