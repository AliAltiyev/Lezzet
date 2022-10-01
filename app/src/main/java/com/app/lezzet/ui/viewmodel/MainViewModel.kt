package com.app.lezzet.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.lezzet.data.Repository
import com.app.lezzet.domain.model.FoodRecipe
import com.app.lezzet.util.Constants.Companion.API_KEY_LIMITED
import com.app.lezzet.util.Constants.Companion.ERROR_402
import com.app.lezzet.util.Constants.Companion.NO_RESULT
import com.app.lezzet.util.Constants.Companion.TIME_OUT
import com.app.lezzet.util.NetworkResult
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    application: Application,
    private val repository: Repository
) : AndroidViewModel(application) {

    val recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {

        recipesResponse.value = NetworkResult.Loading()

        if (hasInternetConnection()) {
            try {
                val response = repository.remoteDataSource.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)

            } catch (e: Exception) {
                recipesResponse.value = NetworkResult.Error(e.message)
            }
        } else {
            recipesResponse.value = NetworkResult.Error("No Internet connection")
        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error(TIME_OUT)
            }
            response.body()!!.results.isNullOrEmpty() -> {
                return NetworkResult.Error(NO_RESULT)
            }
            response.code() == ERROR_402 -> {
                return NetworkResult.Error(API_KEY_LIMITED)
            }
            response.isSuccessful -> {
                val foodRecipe = response.body()
                return NetworkResult.Success(foodRecipe)
            }
            else -> {
                return NetworkResult.Error(response.message().toString())
            }
        }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager =
            getApplication<Application>().getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}