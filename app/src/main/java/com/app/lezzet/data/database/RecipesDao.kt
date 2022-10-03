package com.app.lezzet.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.lezzet.data.database.entity.FoodRecipeRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert
    suspend fun insertRecipes(foodRecipe: FoodRecipeRoomModel)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun getRecipes(): Flow<List<FoodRecipeRoomModel>>

}