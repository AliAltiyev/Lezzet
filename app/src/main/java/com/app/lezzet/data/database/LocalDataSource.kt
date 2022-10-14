package com.app.lezzet.data.database

import com.app.lezzet.data.database.entity.FavoritesRoomModel
import com.app.lezzet.data.database.entity.FoodRecipeRoomModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: RecipesDao
) {
    suspend fun insertRecipe(recipeRoomModel: FoodRecipeRoomModel) {
        dao.insertRecipes(recipeRoomModel)
    }

    fun getAllRecipes(): Flow<List<FoodRecipeRoomModel>> {
        return dao.getRecipes()
    }

    suspend fun insertToFavorite(favorite: FavoritesRoomModel) {
        dao.insertToFavorite(favorite)
    }

    suspend fun removeFromFavorite(favorite: FavoritesRoomModel) {
        dao.deleteFavorite(favorite)
    }

    suspend fun removeAllFromFavorite() {
        dao.deleteAllFromFavorites()
    }

    fun getAllFromFavorites(): Flow<List<FavoritesRoomModel>> {
        return dao.getAllFromFavorites()
    }
}