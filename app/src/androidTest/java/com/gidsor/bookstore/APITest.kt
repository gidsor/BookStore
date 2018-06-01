package com.gidsor.bookstore

import com.gidsor.bookstore.data.network.StatusTask
import org.junit.Assert
import org.junit.Test

class APITest {
    @Test
    fun status() {
        Assert.assertEquals("{\"status\":\"ok\"}", StatusTask().execute().get().toString())
    }
}