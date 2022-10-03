package com.app.lezzet.data.database

import com.app.lezzet.data.database.entity.FoodRecipeRoomModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val dao: RecipesDao
) {
    suspend fun insertRecipe(recipeRoomModel: FoodRecipeRoomModel) {
        dao.insertRecipes(recipeRoomModel)
    }

    fun getRecipes(): Flow<List<FoodRecipeRoomModel>> {
        return dao.getRecipes()
    }
}