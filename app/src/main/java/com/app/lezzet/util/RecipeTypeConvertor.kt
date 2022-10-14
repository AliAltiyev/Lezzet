package com.app.lezzet.util

import androidx.room.TypeConverter
import com.app.lezzet.data.database.entity.FavoritesRoomModel
import com.app.lezzet.domain.model.FoodRecipe
import com.app.lezzet.domain.model.Result
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeTypeConvertor {

    private var gson = Gson()

    @TypeConverter
    fun fromFoodRecipeModelToString(foodRecipeRoomModel: FoodRecipe): String {
        return gson.toJson(foodRecipeRoomModel)
    }

    @TypeConverter
    fun fromStringToFoodRecipeModel(string: String): FoodRecipe {
        val typeToken = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(string, typeToken)
    }

    @TypeConverter
    fun fromResultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun fromStringToResult(string: String): Result {
        val typeToken = object : TypeToken<Result>() {}.type
        return gson.fromJson(string, typeToken)
    }
}