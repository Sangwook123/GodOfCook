package org.mtc.godofcook.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.mtc.godofcook.data.model.local.FoodInfoEntity

@Dao
interface FoodInfoDao {
    @Query("SELECT * FROM food_info")
    fun getAll(): Flow<List<FoodInfoEntity>>

    @Query("SELECT * FROM food_info WHERE name LIKE '%' || :input || '%'")
    fun getContainInput(input: String): Flow<List<FoodInfoEntity>>

    @Query("SELECT * FROM food_info WHERE category LIKE :inputCategory")
    fun getEqualCategory(inputCategory: String): Flow<List<FoodInfoEntity>>

    @Query("SELECT * FROM food_info WHERE subCategory LIKE :inputSubCategory")
    fun getEqualSubCategory(inputSubCategory: String): Flow<List<FoodInfoEntity>>

    @Query("SELECT * FROM food_info WHERE id = :id")
    fun getFoodById(id: Int): Flow<FoodInfoEntity>

    @Insert
    fun addFood(foodInfoEntity: FoodInfoEntity)

    @Insert
    fun addFoods(friendList: List<FoodInfoEntity>)

    @Delete
    fun deleteFood(foodInfoEntity: FoodInfoEntity)

    @Query("DELETE FROM food_info WHERE id = :id")
    fun deleteFoodById(id: Int)

    @Query("DELETE FROM food_info")
    fun deleteAll()
}