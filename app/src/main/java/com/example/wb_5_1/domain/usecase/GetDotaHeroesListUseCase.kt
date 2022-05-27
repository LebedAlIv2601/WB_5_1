package com.example.wb_5_1.domain.usecase

import com.example.wb_5_1.domain.model.DotaHeroModelDomain
import com.example.wb_5_1.domain.repository.DotaRepository
import javax.inject.Inject

class GetDotaHeroesListUseCase @Inject constructor(private val repository: DotaRepository) {

    suspend fun execute(): List<DotaHeroModelDomain>{
        return repository.getDotaHeroes()
    }
}