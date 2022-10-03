package com.app.lezzet.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.lezzet.domain.model.FoodRecipe
import com.app.lezzet.util.Constants.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class FoodRecipeRoomModel(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}
