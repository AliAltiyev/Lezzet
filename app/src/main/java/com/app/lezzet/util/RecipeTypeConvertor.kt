package com.app.lezzet.util

import androidx.room.TypeConverter
import com.app.lezzet.domain.model.FoodRecipe
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeTypeConvertor {

    var gson = Gson()

    @TypeConverter
    fun toString(foodRecipeRoomModel: FoodRecipe): String {
        return gson.toJson(foodRecipeRoomModel)
    }

    @TypeConverter
    fun toFoodRecipeModel(string: String): FoodRecipe {
        val typeToken = object : TypeToken<FoodRecipe>() {}.type
        return gson.fromJson(string, typeToken)
    }
}