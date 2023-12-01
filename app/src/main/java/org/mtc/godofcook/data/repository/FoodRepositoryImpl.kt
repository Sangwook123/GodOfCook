package org.mtc.godofcook.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.mtc.godofcook.data.datasource.FoodDataSource
import org.mtc.godofcook.data.model.local.FoodInfoEntity.Companion.toFoodInfoEntity
import org.mtc.godofcook.data.model.local.FoodInfoEntity.Companion.toFoodInfoEntityList
import org.mtc.godofcook.data.model.local.FoodInfoEntity.Companion.toFoodList
import org.mtc.godofcook.domain.entity.Food
import org.mtc.godofcook.domain.repository.FoodRepository
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodDataSource: FoodDataSource
) : FoodRepository {
    override suspend fun getAll(): Flow<List<Food>> = foodDataSource.getAll().map { data ->
        toFoodList(data)
    }

    override suspend fun getContainInput(input: String): Flow<List<Food>> =
        foodDataSource.getContainInput(input).map { data ->
            toFoodList(data)
        }

    override suspend fun getEqualCategory(inputCategory: String): Flow<List<Food>> =
        foodDataSource.getEqualCategory(inputCategory).map { data ->
            toFoodList(data)
        }

    override suspend fun getEqualSubCategory(inputSubCategory: String): Flow<List<Food>> =
        foodDataSource.getEqualSubCategory(inputSubCategory).map { data ->
            toFoodList(data)
        }

    override suspend fun getFoodById(id: Int): Flow<Food?> =
        foodDataSource.getFoodById(id).map {
            it?.toFood()
        }
    override suspend fun addFood(food: Food) = foodDataSource.addFood(toFoodInfoEntity(food))

    override suspend fun addFoods(foodList: List<Food>) = foodDataSource.addFoods(
        toFoodInfoEntityList(foodList)
    )

    override suspend fun deleteFood(food: Food) = foodDataSource.deleteFood(toFoodInfoEntity(food))

    override suspend fun deleteFoodById(id: Int) = foodDataSource.deleteFoodById(id)

    override suspend fun deleteAll() = foodDataSource.deleteAll()
}