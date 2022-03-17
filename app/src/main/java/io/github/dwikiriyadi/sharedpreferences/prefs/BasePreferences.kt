package io.github.dwikiriyadi.sharedpreferences.prefs

import android.util.Log
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty
import kotlin.reflect.jvm.kotlinFunction

open class BasePreferences(delegate : BasePreferencesContract) : BasePreferencesContract by delegate {
    private val editor = sharedPref.edit()

    fun clear() {
        editor.clear().apply()
    }

    fun <T> propertyDelegate(
        defaultValue: T,
        key: String? = null
    ): ReadWriteProperty<BasePreferences, T> {
        return object : ReadWriteProperty<BasePreferences, T> {
            override fun getValue(thisRef: BasePreferences, property: KProperty<*>): T {
                Log.e("GET", defaultValue!!::class.simpleName ?: "")

                val getFunction =
                    sharedPref::class.java.methods.find { it.name == "get${defaultValue!!::class.simpleName}" }?.kotlinFunction
                return getFunction?.call(sharedPref, key ?: property.name, defaultValue) as T
            }

            override fun setValue(thisRef: BasePreferences, property: KProperty<*>, value: T) {
                Log.e("SET", value!!::class.simpleName ?: "")

                val setFunction =
                    editor::class.java.methods.find { it.name == "put${value!!::class.simpleName}" }?.kotlinFunction

                setFunction?.call(editor, key ?: property.name, value)
                editor.apply()
            }
        }
    }

    fun listOfStringDelegate(
        defaultValue: List<String>,
        key: String? = null
    ): ReadWriteProperty<BasePreferences, List<String>> {
        return object : ReadWriteProperty<BasePreferences, List<String>> {
            override fun getValue(thisRef: BasePreferences, property: KProperty<*>): List<String> {
                return sharedPref.getStringSet(
                    key ?: property.name,
                    defaultValue.toSet()
                )?.toList() ?: listOf()
            }

            override fun setValue(
                thisRef: BasePreferences,
                property: KProperty<*>,
                value: List<String>
            ) {
                editor.putStringSet(key ?: property.name, value.toSet()).apply()
            }
        }
    }
}