package com.duybui.doapp.data.network

import com.duybui.doapp.data.model.User
import com.duybui.doapp.data.response.BaseResponseObject
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/api")
    suspend fun getRandomUser(@Query("results") results: Int): BaseResponseObject<List<User>>
}