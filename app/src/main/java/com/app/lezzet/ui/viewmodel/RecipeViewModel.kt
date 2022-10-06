package com.app.lezzet.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.lezzet.data.database.Datastore
import com.app.lezzet.util.Constants
import com.app.lezzet.util.Constants.Companion.QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val dataStore: Datastore
) : ViewModel() {
    val readMealAndCountry = dataStore.readMealAndCountry

    fun saveMealAndDietType(mealType: String, mealTypeId: Int, country: String, countryId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStore.saveMealAndCountry(mealType, mealTypeId, country, countryId)
        }

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[Constants.QUERY_NUMBER] = "200"
        queries[Constants.QUERY_API_KEY] = Constants.API_KEY
        queries[Constants.QUERY_TYPE] = "salad"
        queries[Constants.QUERY_COUSINE] = "Arabic"
        queries[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[Constants.QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }

    fun applySearchQuaries(query: String): HashMap<String, String> {
        val hashMap = HashMap<String, String>()
        hashMap[Constants.QUERY_NUMBER] = "200"
        hashMap[Constants.QUERY_API_KEY] = Constants.API_KEY
        hashMap[Constants.QUERY_ADD_RECIPE_INFORMATION] = "true"
        hashMap[Constants.QUERY_FILL_INGREDIENTS] = "true"
        hashMap[QUERY] = query
        return hashMap
    }
}