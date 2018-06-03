package com.gidsor.bookstore.data.database

import com.gidsor.bookstore.data.model.Composition
import com.gidsor.bookstore.data.model.User
import com.gidsor.bookstore.data.network.LibraryTask
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object LibraryArrayData {
    private var library: ArrayList<Composition> = arrayListOf()

    fun updateLibrary(user: User) {
        library = arrayListOf()

        val library1 = LibraryTask().execute(user.id.toString(), "1").get()
        val library2 = LibraryTask().execute(user.id.toString(), "2").get()
        val library3 = LibraryTask().execute(user.id.toString(), "3").get()

        val compositionsType = object : TypeToken<ArrayList<Composition>>() {}.type

        if (!library1.isNull("result")) {
            val arr = library1.getJSONArray("result")
            val compositionsLibrary = Gson().fromJson<ArrayList<Composition>>(arr.toString(), compositionsType)
            for (c in compositionsLibrary) {
                c.lib = "1"
            }
            library.addAll(compositionsLibrary)
        }

        if (!library2.isNull("result")) {
            val arr = library2.getJSONArray("result")
            val compositionsLibrary = Gson().fromJson<ArrayList<Composition>>(arr.toString(), compositionsType)
            for (c in compositionsLibrary) {
                c.lib = "2"
            }
            library.addAll(compositionsLibrary)

        }

        if (!library3.isNull("result")) {
            val arr = library3.getJSONArray("result")
            val compositionsLibrary = Gson().fromJson<ArrayList<Composition>>(arr.toString(), compositionsType)
            for (c in compositionsLibrary) {
                c.lib = "3"
            }
            library.addAll(compositionsLibrary)

        }
    }

    fun getLibrary(): ArrayList<Composition> {
        return library
    }
}
