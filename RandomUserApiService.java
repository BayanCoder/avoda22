
package com.example.avoda2.api;

import com.example.avoda2.model.RandomUserResponse;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
public interface RandomUserApiService {
    @GET("api")
    suspend fun getRandomUser(): Response<RandomUserResponse>
}
    object RandomUserApi {
private const val BASE_URL = "https://randomuser.me/"

private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val retrofitService: RandomUserApiService by lazy {
        retrofit.create(RandomUserApiService::class.java)
        }
        }
