package org.mtc.godofcook.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mtc.godofcook.data.database.FoodDataBase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {
    @Singleton
    @Provides
    fun provideDataBase(
        @ApplicationContext context: Context
    ): FoodDataBase =
        Room.databaseBuilder(context, FoodDataBase::class.java, "food.db").build()

    @Singleton
    @Provides
    fun provideFriendDao(foodDataBase: FoodDataBase) = foodDataBase.friendInfoDao()
}