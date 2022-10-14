package com.app.lezzet.data.database

import androidx.room.*
import com.app.lezzet.data.database.entity.FavoritesRoomModel
import com.app.lezzet.data.database.entity.FoodRecipeRoomModel
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {

    @Insert
    suspend fun insertRecipes(foodRecipe: FoodRecipeRoomModel)

    @Query("SELECT * FROM recipes_table ORDER BY id ASC")
    fun getRecipes(): Flow<List<FoodRecipeRoomModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToFavorite(favorites: FavoritesRoomModel)

    @Query("SELECT * FROM  favorites ORDER BY id ASC")
    fun getAllFromFavorites(): Flow<List<FavoritesRoomModel>>

    @Delete
    suspend fun deleteFavorite(favorites: FavoritesRoomModel)

    @Query("DELETE FROM favorites")
    suspend fun deleteAllFromFavorites()
}