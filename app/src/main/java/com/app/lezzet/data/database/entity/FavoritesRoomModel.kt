package com.app.lezzet.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.lezzet.domain.model.Result
import com.app.lezzet.util.Constants.Companion.FAVORITES_DATABASE_TABLE_NAME

@Entity(tableName = FAVORITES_DATABASE_TABLE_NAME)
data class FavoritesRoomModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @ColumnInfo(name = "Result")
    val result: Result
) {

}