package com.example.kotlin_basic.data.remote
import com.example.kotlin_basic.data.model.LoginRequest
import com.example.kotlin_basic.data.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>
}