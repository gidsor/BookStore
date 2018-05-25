package com.gidsor.bookstore.data.model

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class User(val id: Int = -1, val email: String = "", val realName: String = "Аноним", val phone: String = "")