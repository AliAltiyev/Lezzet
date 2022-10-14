package com.app.lezzet.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.app.lezzet.data.Repository
import com.app.lezzet.data.database.entity.FavoritesRoomModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val getAllFromFavorites: LiveData<List<FavoritesRoomModel>> =
        repository.localDataSource.getAllFromFavorites().asLiveData()

    fun insertToFavorites(favorites: FavoritesRoomModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localDataSource.insertToFavorite(favorites)
        }
    }

    fun removeFromFavorites(favorites: FavoritesRoomModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localDataSource.removeFromFavorite(favorites)
        }
    }

    fun removeAllFromFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.localDataSource.removeAllFromFavorite()
        }
    }

}