package org.mtc.godofcook.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.mtc.godofcook.domain.entity.Food

@Entity(tableName = "food_info")
data class FoodInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val category: String,
    val subCategory: String,
    val recipe: String
){
    fun toFood() = Food(
        this.id,
        this.name,
        this.category,
        this.subCategory,
        this.recipe
    )

    companion object {
        fun toFoodList(foodInfoList: List<FoodInfoEntity>) = foodInfoList.map { data ->
            Food(
                data.id,
                data.name,
                data.category,
                data.subCategory,
                data.recipe
            )
        }

        fun toFoodInfoEntityList(foodList: List<Food>) = foodList.map { data ->
            FoodInfoEntity(
                data.id,
                data.name,
                data.category,
                data.subCategory,
                data.recipe
            )
        }

        fun toFoodInfoEntity(food: Food) = FoodInfoEntity(
            food.id,
            food.name,
            food.category,
            food.subCategory,
            food.recipe
        )
    }
}
