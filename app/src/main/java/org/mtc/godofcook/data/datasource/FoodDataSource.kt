package org.mtc.godofcook.data.datasource

import kotlinx.coroutines.flow.Flow
import org.mtc.godofcook.data.model.local.FoodInfoEntity

interface FoodDataSource {
    suspend fun getAll(): Flow<List<FoodInfoEntity>>

    suspend fun getContainInput(input: String): Flow<List<FoodInfoEntity>>

    suspend fun getEqualCategory(inputCategory: String): Flow<List<FoodInfoEntity>>

    suspend fun getEqualSubCategory(inputSubCategory: String): Flow<List<FoodInfoEntity>>

    suspend fun getFoodById(id: Int): Flow<FoodInfoEntity>

    suspend fun addFood(foodInfoEntity: FoodInfoEntity)

    suspend fun addFoods(friendList: List<FoodInfoEntity>)

    suspend fun deleteFood(foodInfoEntity: FoodInfoEntity)

    suspend fun deleteFoodById(id: Int)

    suspend fun deleteAll()
}