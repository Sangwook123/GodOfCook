package org.mtc.godofcook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mtc.godofcook.data.repository.FoodRepositoryImpl
import org.mtc.godofcook.domain.repository.FoodRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindsFoodRepository(foodRepository: FoodRepositoryImpl): FoodRepository
}