package org.mtc.godofcook.domain.repository

import kotlinx.coroutines.flow.Flow
import org.mtc.godofcook.domain.entity.Food

interface FoodRepository {
    suspend fun getAll(): Flow<List<Food>>

    suspend fun getContainInput(input: String): Flow<List<Food>>

    suspend fun getEqualCategory(inputCategory: String): Flow<List<Food>>

    suspend fun getEqualSubCategory(inputSubCategory: String): Flow<List<Food>>

    suspend fun getFoodById(id: Int): Flow<Food?>

    suspend fun addFood(food: Food)

    suspend fun addFoods(foodList: List<Food>)

    suspend fun deleteFood(food: Food)

    suspend fun deleteFoodById(id: Int)

    suspend fun deleteAll()
}