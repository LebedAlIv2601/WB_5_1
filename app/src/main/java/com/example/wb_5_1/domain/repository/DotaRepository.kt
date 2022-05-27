package com.example.wb_5_1.domain.repository

import com.example.wb_5_1.domain.model.DotaHeroModelDomain

interface DotaRepository {

    suspend fun getDotaHeroes(): List<DotaHeroModelDomain>

}