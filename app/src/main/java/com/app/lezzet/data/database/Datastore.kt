package com.app.lezzet.data.database

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.emptyPreferences
import androidx.datastore.preferences.preferencesKey
import com.app.lezzet.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.app.lezzet.util.Constants.Companion.PREFERENCES_COUNRTY_ID
import com.app.lezzet.util.Constants.Companion.PREFERENCES_COUNTRY
import com.app.lezzet.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.app.lezzet.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.app.lezzet.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

@ActivityRetainedScoped
class Datastore @Inject constructor(@ApplicationContext context: Context) {

    private object PreferenceKeys {
        val selectedMealType = preferencesKey<String>(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = preferencesKey<Int>(PREFERENCES_MEAL_TYPE_ID)
        val selectedCountry = preferencesKey<String>(PREFERENCES_COUNTRY)
        val selectedCountryId = preferencesKey<Int>(PREFERENCES_COUNRTY_ID)
        val backOnline = preferencesKey<Boolean>(PREFERENCES_BACK_ONLINE)
    }

    private val dataStore: DataStore<androidx.datastore.preferences.Preferences> =
        context.createDataStore(
            name = PREFERENCES_NAME,
        )

    suspend fun saveMealAndCountry(
        mealType: String,
        mealTypeId: Int,
        country: String,
        countryId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.selectedMealType] = mealType
            preferences[PreferenceKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferenceKeys.selectedCountry] = country
            preferences[PreferenceKeys.selectedCountryId] = countryId
        }
    }

    suspend fun saveBackOnline(backOnline: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKeys.backOnline] = backOnline
        }
    }

    val readMealAndCountry: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType =
                preferences[PreferenceKeys.selectedMealType] ?: "DEFAULT_MEAL_TYPE"
            val selectedMealTypeId = preferences[PreferenceKeys.selectedMealTypeId] ?: 0
            val selectedDietType =
                preferences[PreferenceKeys.selectedCountry] ?: "DEFAULT_DIET_TYPE"
            val selectedDietTypeId = preferences[PreferenceKeys.selectedCountryId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }

    val readBackOnline: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val backOnline = preferences[PreferenceKeys.backOnline] ?: false
            backOnline
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedCountry: String,
    val selectedCountryId: Int
)
