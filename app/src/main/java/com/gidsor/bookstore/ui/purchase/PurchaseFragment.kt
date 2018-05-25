package com.gidsor.bookstore.ui.purchase

import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.db.BookArrayData
import com.gidsor.bookstore.data.db.OrderArrayData
import com.gidsor.bookstore.data.model.Order
import com.gidsor.bookstore.ui.main.MainActivity

class PurchaseFragment : ListFragment() {

    private lateinit var orderItems: ArrayList<Order>
    private lateinit var adapter: OrderAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_purchase, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = OrderAdapter(view!!.context)
        listAdapter = adapter
        var makeOrder: Button = view!!.findViewById(R.id.purchase_make_order_button)
        makeOrder.text = "Оформить заказ стоимостью ${OrderArrayData.getCommonPrice()},00 \u20BD"
    }
}