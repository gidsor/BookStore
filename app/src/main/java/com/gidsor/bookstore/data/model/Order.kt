package com.gidsor.bookstore.data.model

data class Order(var id: String,
                 var descriptionPrice: String,
                 var comment: String,
                 var dateOrder: String,
                 var address: String,
                 var price: Int,
                 var dateDelivery: String,
                 var phone: String,
                 var statusDelivery: String,
                 var priceDelivery: Int,
                 var descriptionDelivery: String)