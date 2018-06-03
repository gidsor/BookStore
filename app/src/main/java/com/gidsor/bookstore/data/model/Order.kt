package com.gidsor.bookstore.data.model

import com.google.gson.annotations.SerializedName

class Order(var order: String,
            var comment: String,
            var address: String,
            var price: Int,
            var phone: String,
            @SerializedName("description_price") var descriptionPrice: String,
            @SerializedName("date_order") var dateOrder: String,
            @SerializedName("date_delivery") var dateDelivery: String,
            @SerializedName("status_delivery") var statusDelivery: String,
            @SerializedName("price_delivery") var priceDelivery: Int,
            @SerializedName("description_delivery") var descriptionDelivery: String)