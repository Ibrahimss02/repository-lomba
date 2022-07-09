package com.drunken.repositorylombaraion.network

import com.drunken.repositorylombaraion.models.HistoryLomba
import com.drunken.repositorylombaraion.models.Kelompok
import com.drunken.repositorylombaraion.models.Lomba
import com.drunken.repositorylombaraion.models.User
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://api-repository-lomba-raion.herokuapp.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("lomba")
    suspend fun getAllLomba(): List<Lomba>?

    @GET("lomba/{lombaId}")
    suspend fun getSpecificLomba(@Path("lombaId") lombId: String): Lomba?

    @GET("kelompok/{kelompokId}")
    suspend fun getKelompok(@Path("kelompokId") kelompokId: String): Kelompok?

    @GET("user/{userId}")
    suspend fun getUser(@Path("userId") userId: String): User?

    @POST("lomba")
    suspend fun createNewLomba(@Body lomba: Lomba)

    @POST("lomba/{lombaId}/history")
    suspend fun addNewHistoryLomba(@Path("lombaId") lombaId: String, @Body historyLomba: HistoryLomba)

    @POST("kelompok")
    suspend fun createNewKelompok(@Body kelompok: Kelompok)

    @POST("user")
    suspend fun createNewUser(@Body user: User)

    @POST("kelompok/{kelompokId}/anggota")
    suspend fun addNewAnggotaKelompok(@Path("kelompokId") id: String, @Field("id_kelompok") idKelompok: String, @Field("id_user") idUser: String)
}

object RepositoryLombaApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}