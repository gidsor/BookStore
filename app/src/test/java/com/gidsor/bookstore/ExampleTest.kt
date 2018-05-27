package com.gidsor.bookstore

import com.gidsor.bookstore.data.network.HTTPRequestAPI
import org.junit.Assert
import org.junit.Test

class ExampleTest {
    @Test
    fun status() {
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.status().toString())
    }
}