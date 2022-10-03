package com.app.lezzet.util

class Constants {
    companion object {


        //Database
        const val DATABASE_NAME = "recipes"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "recipes_table"

        //Network
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
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"
    }
}