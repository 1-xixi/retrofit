package com.example.retrofit.api

import com.example.retrofit.data.Post
// 关键：替换为 Retrofit 的泛型 Response
import retrofit2.Response
import retrofit2.http.GET

//规则
interface APIService {
    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}