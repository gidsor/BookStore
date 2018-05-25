package com.gidsor.bookstore.ui.purchase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Order

class OrderItemFragment : Fragment() {
    lateinit var orderToShow: Order
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_purchase_order_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOrder(orderToShow)
    }

    fun setOrder(order: Order) {

    }
}