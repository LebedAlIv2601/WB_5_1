package com.example.wb_5_1.data.repository

import com.example.wb_5_1.data.network.DotaApiHelper
import com.example.wb_5_1.domain.model.DotaHeroModelDomain
import com.example.wb_5_1.domain.repository.DotaRepository
import javax.inject.Inject

@ExperimentalStdlibApi
class DotaRepositoryImpl @Inject constructor(private val apiHelper: DotaApiHelper): DotaRepository {

    override suspend fun getDotaHeroes(): List<DotaHeroModelDomain> {
        return apiHelper.getDotaHeroes() ?: emptyList()
    }
}