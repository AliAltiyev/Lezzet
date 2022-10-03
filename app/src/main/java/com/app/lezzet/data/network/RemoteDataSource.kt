package com.app.lezzet.data.network

import com.app.lezzet.data.network.RecipeApi
import com.app.lezzet.domain.model.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val recipeApi: RecipeApi
) {

    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return recipeApi.getRecipes(queries)
    }

}