package com.example.assesmenttask.data.remote

import com.example.assesmenttask.data.model.UserDetails
import com.example.assesmenttask.data.service.ApiService
import com.example.assesmenttask.handler.CustomResponse
import com.example.assesmenttask.handler.LocalException
import com.example.assesmenttask.mapper.UserDetailsListMapper

class ApiRepository(private val apiService: ApiService) {
    suspend fun getUserDetails() : CustomResponse<MutableList<UserDetails?>, LocalException>{
        return UserDetailsListMapper.map(apiService.getUserList())
    }
}