package org.mtc.godofcook.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.mtc.godofcook.data.datasource.FoodDataSource
import org.mtc.godofcook.data.datasourceimpl.FoodDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindsFoodDataSource(foodDataSource: FoodDataSourceImpl): FoodDataSource
}