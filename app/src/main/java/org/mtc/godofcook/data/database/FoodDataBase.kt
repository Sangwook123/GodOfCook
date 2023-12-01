package org.mtc.godofcook.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.mtc.godofcook.data.model.local.FoodInfoEntity

@Database(
    entities = [
        FoodInfoEntity::class
    ], version = 1
)
abstract class FoodDataBase : RoomDatabase() {
    abstract fun friendInfoDao(): FoodInfoDao
}