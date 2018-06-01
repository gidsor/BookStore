package com.gidsor.bookstore.data.model

import com.gidsor.bookstore.utils.AppConstants

data class User(val id: Int = AppConstants.DEFAULT_USER_ID,
                val email: String = "",
                val realName: String = AppConstants.DEFAULT_USER_NAME,
                val phone: String = "")