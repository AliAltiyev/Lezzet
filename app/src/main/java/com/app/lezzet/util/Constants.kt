package com.app.lezzet.util

class Constants {
    companion object {
        //Database
        const val FAVORITES_DATABASE_TABLE_NAME = "favorites"
        const val DATABASE_NAME = "recipes"
        const val DATABASE_VERSION = 2
        const val TABLE_NAME = "recipes_table"
        //Network
        const val INGREDIENTS_BASE_URL  = "https://spoonacular.com/cdn/ingredients_100x100/"
        const val BASE_URL = "https://api.spoonacular.com"
        const val API_KEY = "f6f748417588452fa56df637a8125a7c"
        const val ERROR_402 = 402
        const val API_KEY_LIMITED = "Api Key Limited"
        const val NO_RESULT = "No result"
        const val TIME_OUT = "Time out"
        const val CROSS_FADE_DURATION_MILLIS = 650
        const val NO_INTERNET_CONNECTION = "Something went wrong,Try again..."
        //Api Quires/////////////////
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_COUSINE = "cuisine"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"
        const val QUERY = "query"
        const val PREFERENCES_NAME = "foody_preferences"
        const val PREFERENCES_MEAL_TYPE = "mealType"
        const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
        const val PREFERENCES_COUNTRY = "country"
        const val PREFERENCES_COUNRTY_ID = "countryId"
        const val PREFERENCES_BACK_ONLINE = "backOnline"

        //Default meal type and country
        const val DEFAULT_MEAL_TYPE = "salad"
        const val DEFAULT_COUNTRY = "America"

        //Fragment title names
        const val OVERVIEW_FRAGMENT_TITLE = "Overview"
        const val INSTRUCTIONS_FRAGMENT_TITLE = "Instructions"
        const val INGREDIENTS_FRAGMENT_TITLE = "Ingredients"
        //Details Fragment Bundle KEY
        const val BUNDLE_KEY = "data"
    }
}