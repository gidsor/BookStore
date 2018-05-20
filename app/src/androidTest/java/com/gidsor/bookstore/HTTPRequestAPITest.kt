package com.gidsor.bookstore

import com.gidsor.bookstore.data.network.HTTPRequestAPI
import org.junit.Assert
import org.junit.Test

class HTTPRequestAPITest {
    @Test
    fun getStatus() {
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.getStatus().toString())
    }
}