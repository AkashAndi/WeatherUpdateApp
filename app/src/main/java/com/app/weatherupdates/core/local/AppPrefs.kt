package com.app.weatherupdates.core.local

import android.content.Context
import android.content.SharedPreferences

class AppPrefs(context: Context) {

    private val sharedPreferences: SharedPreferences

    var unitSelected: String
        set(value) = put(UNIT_SELECTED, value)
        get() = get(UNIT_SELECTED, String::class.java)

    init {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPreferences.getString(key, "")
            Boolean::class.java -> sharedPreferences.getBoolean(key, false)
            Float::class.java -> sharedPreferences.getFloat(key, 0f)
            Double::class.java -> sharedPreferences.getFloat(key, 0f)
            Int::class.java -> sharedPreferences.getInt(key, 0)
            Long::class.java -> sharedPreferences.getLong(key, 0L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPreferences.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val PREFS_NAME = "WeatherAppSharedPrefernces"
        const val UNIT_SELECTED = "UNIT_SELECTED"
    }
}