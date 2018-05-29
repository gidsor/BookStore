package com.gidsor.bookstore.ui.store

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.SetReviewTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user
import com.gidsor.bookstore.ui.main.MainActivity

class AddReviewDialog : DialogFragment() {
    companion object {
        lateinit var book: Book
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_add_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.review_cancel).setOnClickListener { _ ->
            dismiss()
        }

        view.findViewById<Button>(R.id.review_add).setOnClickListener { _ ->
            val mark = view.findViewById<RatingBar>(R.id.review_add_rating).rating.toInt().toString()
            val text = view.findViewById<EditText>(R.id.review_add_text).text.toString()
            SetReviewTask().execute(user.id.toString(), book.composition.toString(), mark, text).get()
            dismiss()
            MainActivity.loadBookItemFragment(book)
        }
    }

}