package com.gidsor.bookstore.ui.account

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.database.BookArrayData
import com.gidsor.bookstore.data.database.OrderOfUserArrayData
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.GetOrderTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user
import com.squareup.picasso.Picasso

class MyOrdersDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_my_orders, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showMyOrders(user, view)
    }

    private fun showMyOrders(currentUser: User, view: View) {
        val myOrders: LinearLayout = view.findViewById(R.id.my_orders_container)
        myOrders.removeAllViewsInLayout()

        OrderOfUserArrayData.updateOrdersOfUser(currentUser)

        for (i in OrderOfUserArrayData.getOrdersOfUser()) {
            val newTextView = TextView(view.context)
            val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            paramsOfTextView.setMargins(10, 10, 0, 0)
            newTextView.layoutParams = paramsOfTextView
            newTextView.text = """Дата заказа: ${i.dateOrder}
Цена заказа: ${i.price}
Дата доставки: ${i.dateDelivery}
Статус доставки: ${i.statusDelivery}
Вариант доставки: ${i.descriptionDelivery}
Цена доставки: ${i.priceDelivery}
Адрес доставки: ${i.address}
Комментарий: ${i.comment}
Статус оплаты: ${i.descriptionPrice}
Книги:"""

            myOrders.addView(newTextView)

            val inflater = LayoutInflater.from(view.context)
            val order = GetOrderTask().execute(currentUser.id.toString(), i.order).get().getJSONArray("result")

            for (i in 0 until order.length()) {
                val book: Book = BookArrayData.getBook(order.getJSONObject(i).getString("isbn"))
                val countBook = order.getJSONObject(i).getString("count")

                val orderItemLayout = inflater.inflate(R.layout.fragment_order, null, false)

                val imageView = orderItemLayout.findViewById<ImageView>(R.id.order_image)
                val urlImageView  = book.image
                Picasso.get().load(urlImageView).placeholder(R.drawable.not_found).error(R.drawable.not_found)
                        .fit().centerInside()
                        .into(imageView)

                orderItemLayout.findViewById<TextView>(R.id.order_title).text = book.title
                orderItemLayout.findViewById<TextView>(R.id.order_author).text = book.author
                orderItemLayout.findViewById<TextView>(R.id.order_count).text = "x $countBook"

                myOrders.addView(orderItemLayout)
            }
        }
        if (OrderOfUserArrayData.getOrdersOfUser().size == 0) {
            val newTextView = TextView(view.context)
            val paramsOfTextView = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            paramsOfTextView.setMargins(10, 10, 0, 0)
            newTextView.layoutParams = paramsOfTextView
            newTextView.text = "Пусто"
            myOrders.addView(newTextView)
        }
    }
}