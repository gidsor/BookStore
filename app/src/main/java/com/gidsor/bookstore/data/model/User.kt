package com.gidsor.bookstore.data.model

import com.gidsor.bookstore.utils.AppConstants
import com.google.gson.annotations.SerializedName

class User(val id: Int = AppConstants.DEFAULT_USER_ID,
           var realName: String = AppConstants.DEFAULT_USER_NAME,
           val email: String = "",
           val phone: String = "",
           @SerializedName("firstname") val firstName: String = "",
           @SerializedName("lastname") val lastName: String = "",
           @SerializedName("patronymic") val middleName: String = "")