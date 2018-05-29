package com.gidsor.bookstore.ui.store

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import com.gidsor.bookstore.R
import com.gidsor.bookstore.data.model.Book
import com.gidsor.bookstore.data.network.SetReviewTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user

class AddReviewDialog : DialogFragment() {
    companion object {
        lateinit var book: Book
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        val inflater = activity!!.layoutInflater

        builder.setView(inflater.inflate(R.layout.dialog_add_review, null))
        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.review_cancel).setOnClickListener { _ ->
            dismiss()
        }

        view.findViewById<Button>(R.id.review_add).setOnClickListener { v ->
            val mark = v.findViewById<RatingBar>(R.id.review_add_rating).rating.toInt().toString()
            val text = v.findViewById<EditText>(R.id.review_add_text).text.toString()
            SetReviewTask().execute(user.id.toString(), book.composition.toString(), mark, text).get()
        }
    }

}