package com.tst.apiAC

import retrofit2.Call
import retrofit2.http.GET

interface GetTest {
    @GET("5d8a826630000051b5b9a9b5")
    fun getList() : Call<Array<String>>
}