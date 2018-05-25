package com.gidsor.bookstore.data.db

import com.gidsor.bookstore.data.model.Composition
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.LibraryTask
import com.gidsor.bookstore.ui.account.AccountFragment.Companion.user

class LibraryArrayData {
    companion object {
        private var compositions: ArrayList<Composition>
        private var library1 = LibraryTask().execute(user.id.toString(), "1").get()
        private var library2 = LibraryTask().execute(user.id.toString(), "2").get()
        private var library3 = LibraryTask().execute(user.id.toString(), "3").get()

        init {
            compositions = arrayListOf()
            library1 = LibraryTask().execute(user.id.toString(), "1").get()
            library2 = LibraryTask().execute(user.id.toString(), "2").get()
            library3 = LibraryTask().execute(user.id.toString(), "3").get()
            updateCompositions(user)
        }

        fun updateCompositions(user: User) {
            compositions = arrayListOf()
            library1 = LibraryTask().execute(user.id.toString(), "1").get()
            library2 = LibraryTask().execute(user.id.toString(), "2").get()
            library3 = LibraryTask().execute(user.id.toString(), "3").get()
            if (!library1.isNull("result")) {
                var arr = library1.getJSONArray("result")
                for (i in 0 until arr.length()) {
                    var c = arr.getJSONObject(i)
                    compositions.add(Composition(c.getString("composition"), c.getString("title"), c.getString("author"), "1"))
                }
            }
            if (!library2.isNull("result")) {
                var arr = library2.getJSONArray("result")
                for (i in 0 until arr.length()) {
                    var c = arr.getJSONObject(i)
                    compositions.add(Composition(c.getString("composition"), c.getString("title"), c.getString("author"), "2"))
                }
            }
            if (!library3.isNull("result")) {
                var arr = library3.getJSONArray("result")
                for (i in 0 until arr.length()) {
                    var c = arr.getJSONObject(i)
                    compositions.add(Composition(c.getString("composition"), c.getString("title"), c.getString("author"), "3"))
                }
            }
//            for (i in 0 until 5) {
//                orders.add(Order("1", BookArrayData.getBooks()[i]))
//            }
        }

        fun getCompositions(): ArrayList<Composition> {
            return compositions
        }
    }
}
