package com.app.lezzet.util

import android.util.Log
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import org.jsoup.Jsoup

fun String.toJsoup(): String = Jsoup.parse(this).text()

fun String.translateToRussian(): String? {
    var text: String? = null
    val options = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.ENGLISH)
        .setTargetLanguage(TranslateLanguage.GERMAN)
        .build()
    val englishGermanTranslator = Translation.getClient(options)
    var conditions = DownloadConditions.Builder()
        .requireWifi()
        .build()
    englishGermanTranslator.downloadModelIfNeeded(conditions)
    englishGermanTranslator.translate(this).addOnCompleteListener { task ->
        if (task.isComplete) {
            text = task.result
        }
    }.addOnFailureListener { task ->
        task.localizedMessage?.let { Log.d("task", it) }
    }
    englishGermanTranslator.close()
    return text
}

val Int.Companion.ZERO: Int get() = 0