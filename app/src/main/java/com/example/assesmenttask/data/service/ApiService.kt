package com.example.assesmenttask.data.service

import com.example.assesmenttask.data.model.UserDetails
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUserList(): Response<ArrayList<UserDetails?>>
}