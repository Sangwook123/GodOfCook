package org.mtc.godofcook.data.datasourceimpl

import kotlinx.coroutines.flow.Flow
import org.mtc.godofcook.data.database.FoodInfoDao
import org.mtc.godofcook.data.datasource.FoodDataSource
import org.mtc.godofcook.data.model.local.FoodInfoEntity
import javax.inject.Inject

class FoodDataSourceImpl @Inject constructor(
    private val foodInfoDao: FoodInfoDao
) : FoodDataSource {
    override suspend fun getAll(): Flow<List<FoodInfoEntity>> = foodInfoDao.getAll()

    override suspend fun getContainInput(input: String): Flow<List<FoodInfoEntity>> =
        foodInfoDao.getContainInput(input)

    override suspend fun getEqualCategory(inputCategory: String): Flow<List<FoodInfoEntity>> =
        foodInfoDao.getEqualCategory(inputCategory)

    override suspend fun getEqualSubCategory(inputSubCategory: String): Flow<List<FoodInfoEntity>> =
        foodInfoDao.getEqualSubCategory(inputSubCategory)

    override suspend fun getFoodById(id: Int): Flow<FoodInfoEntity> = foodInfoDao.getFoodById(id)

    override suspend fun addFood(foodInfoEntity: FoodInfoEntity) =
        foodInfoDao.addFood(foodInfoEntity)

    override suspend fun addFoods(friendList: List<FoodInfoEntity>) =
        foodInfoDao.addFoods(friendList)

    override suspend fun deleteFood(foodInfoEntity: FoodInfoEntity) =
        foodInfoDao.deleteFood(foodInfoEntity)

    override suspend fun deleteFoodById(id: Int) = foodInfoDao.deleteFoodById(id)

    override suspend fun deleteAll() = foodInfoDao.deleteAll()
}