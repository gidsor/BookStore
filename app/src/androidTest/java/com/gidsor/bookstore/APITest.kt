package com.gidsor.bookstore

import com.gidsor.bookstore.data.network.HTTPRequestAPI
import org.junit.Assert
import org.junit.Test

class APITest {
    @Test
    fun status() {
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.status().toString())
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.status().toString())
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.status().toString())
    }

    @Test
    fun login() {
        Assert.assertEquals("{\"status\":\"ok\",\"id\":1}", HTTPRequestAPI.login("login1", "pass1").toString())
    }
}