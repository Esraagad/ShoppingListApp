package com.example.shoppinglistapp.data.remote

import com.example.shoppinglistapp.Constants
import com.example.shoppinglistapp.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("/api/")
    suspend fun searchImage(@Query("q") searchQuery:String,
                            @Query("key") apiKey: String = Constants.PIXABAY_API_KEY
    ): Response<ImageResponse>
}