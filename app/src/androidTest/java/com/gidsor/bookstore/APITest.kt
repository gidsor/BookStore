package com.gidsor.bookstore

import com.gidsor.bookstore.data.network.HTTPRequestAPI
import org.junit.Assert
import org.junit.Test

class APITest {
    @Test
    fun status() {
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.status().toString())
    }

    @Test
    fun login() {
        Assert.assertEquals("{\"status\":\"ok\",\"id\":1}", HTTPRequestAPI.login("login1", "pass1").toString())
    }

    @Test
    fun library() {
        Assert.assertEquals("{\"status\":\"ok\",\"result\":[{\"status\":\"Прочитано\",\"book\":\"Мастер и Маргарита\",\"author\":\"Михаил Афанасьевич Булгаков\",\"lib\":\"Валентина Шукшина\"},{\"status\":\"Прочитано\",\"book\":\"И грянул гром\",\"author\":\"Рэй Бредбери\",\"lib\":\"Валентина Шукшина\"}]}", HTTPRequestAPI.library("1").toString())
    }

    @Test
    fun registration() {
        Assert.assertEquals("{\"status\":\"ok\"}", HTTPRequestAPI.registration("qazwsx@mail.ru", "qazwsx").toString())
    }

    @Test
    fun find() {
        Assert.assertEquals("{\"status\":\"ok\",\"id\":[[8,\"Черный человек\",\"Сергей Александрович Есенин\",\"Поэзия\",\"Художественная литература\",\"Русский\"]]}", HTTPRequestAPI.composition(author = "Есенин").toString())
    }
}