package com.example.wb_5_1.data.network

import coil.network.HttpException
import com.example.wb_5_1.utils.Constants.BASE_API
import com.example.wb_5_1.utils.Constants.HERO_STATS_QUERY
import com.example.wb_5_1.data.model.DotaHeroModelData
import com.example.wb_5_1.data.utils.toDomain
import com.example.wb_5_1.domain.model.DotaHeroModelDomain
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import okhttp3.OkHttpClient
import okhttp3.Request
import javax.inject.Inject

class DotaApiHelper @Inject constructor(private val client: OkHttpClient) {

    @ExperimentalStdlibApi
    fun getDotaHeroes(): List<DotaHeroModelDomain>? {
        val request = Request.Builder().url(BASE_API + HERO_STATS_QUERY).build()

        val result: List<DotaHeroModelDomain>?

        try {
            val response = client.newCall(request).execute()

            val json = response.body?.string()

            val moshi = Moshi.Builder().build()
            val adapter = moshi.adapter<List<DotaHeroModelData>>()

            result = adapter.fromJson(json)?.map { it.toDomain() }

        } catch (e: HttpException) {
            return emptyList()
        }

        return result
    }

}