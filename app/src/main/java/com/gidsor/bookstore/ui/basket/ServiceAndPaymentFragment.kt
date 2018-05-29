package com.gidsor.bookstore.ui.basket

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BasketArrayData
import com.gidsor.bookstore.data.network.CreateOrderTask
import com.gidsor.bookstore.data.network.DelFromBasketTask
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
        makeOrder.setOnClickListener { _ ->
            if (user.id != -1) {
                val rg = view!!.findViewById<RadioGroup>(R.id.s_and_p_radio_group)
                val rb = view!!.findViewById<RadioButton>(rg.checkedRadioButtonId)
                val typeOfService = rg.indexOfChild(rb) + 1
                var type = "самовывоз"
                when (typeOfService) {
                    1 -> type = "самовывоз"
                    2 -> type = "доставка по городу"
                    3 -> type = "доставка за пределы"
                    4 -> type = "почта"
                }

                val card = view!!.findViewById<EditText>(R.id.s_and_p_card).text.toString()
                val address = view!!.findViewById<EditText>(R.id.s_and_p_address).text.toString()
                val phone = view!!.findViewById<EditText>(R.id.s_and_p_phone).text.toString()
                val message = view!!.findViewById<EditText>(R.id.s_and_p_message).text.toString()
                CreateOrderTask().execute(user.id.toString(), card, address, phone, message, type).get()
                BasketArrayData.updateOrder(user)
//                for (order in BasketArrayData.getBasket()) {
//                    DelFromBasketTask().execute(user.id.toString(), order.book.isbn).get()
//                }
                Toast.makeText(context, "Заказ был создан успешно", Toast.LENGTH_SHORT)
                MainActivity.loadStoreFragmentWithGenreAndSearch("Все")
            }
        }
    }
}