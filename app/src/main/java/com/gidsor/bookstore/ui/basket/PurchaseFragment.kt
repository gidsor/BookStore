package com.gidsor.bookstore.ui.basket

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BasketArrayData
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user
import com.gidsor.bookstore.ui.adapters.BasketAdapter
import com.gidsor.bookstore.ui.main.MainActivity

class PurchaseFragment : ListFragment() {
    private lateinit var adapter: BasketAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        BasketArrayData.updateOrder(user)
        return inflater.inflate(R.layout.fragment_basket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = BasketAdapter(view!!.context)
        listAdapter = adapter

        val makeOrder: Button = view!!.findViewById(R.id.purchase_make_order_button)
        makeOrder.text = "Оформить заказ"

        makeOrder.setOnClickListener { v ->
            if (user.id != -1 && BasketArrayData.getCommonPrice() != 0) {
                (activity as MainActivity).loadServiceAndPaymentFragment()
            } else {
                Toast.makeText(activity, "Добавьте товар в корзину", Toast.LENGTH_SHORT).show()
            }
        }
    }
}