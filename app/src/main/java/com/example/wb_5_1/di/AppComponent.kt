package com.example.wb_5_1.di

import com.example.wb_5_1.data.repository.DotaRepositoryImpl
import com.example.wb_5_1.domain.repository.DotaRepository
import com.example.wb_5_1.presentation.main.MainFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient

@Component(modules = [DataModule::class, DomainBinds::class])
interface AppComponent{
    fun inject(mainFragment: MainFragment)
}

@Module
class DataModule{
    @Provides
    fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient()
    }
}

@Module
interface DomainBinds{

    @ExperimentalStdlibApi
    @Binds
    fun doaRepositoryImplToInterface(
        repositoryImpl: DotaRepositoryImpl
    ): DotaRepository

}
