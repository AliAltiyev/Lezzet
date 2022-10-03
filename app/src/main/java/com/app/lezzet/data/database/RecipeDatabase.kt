package com.app.lezzet.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.lezzet.data.database.entity.FoodRecipeRoomModel
import com.app.lezzet.util.Constants.Companion.DATABASE_VERSION
import com.app.lezzet.util.RecipeTypeConvertor

@Database(entities = [FoodRecipeRoomModel::class], version = DATABASE_VERSION, exportSchema = false)
@TypeConverters(RecipeTypeConvertor::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun getRecipesDao(): RecipesDao

}