package com.example.retrofit.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // 1. 创建 Gson 转换器
    private val gsonConverter = GsonConverterFactory.create()

    // 2. 创建 Retrofit 对象
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/") // 设置基础 URL
        .addConverterFactory(gsonConverter) // 设置 JSON 转换器
        .build()


    // 3. 创建 ApiService 接口的实现对象
    val apiService: APIService = retrofit.create(APIService::class.java)
}
