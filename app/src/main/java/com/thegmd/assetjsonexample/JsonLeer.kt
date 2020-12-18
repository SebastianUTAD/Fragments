package com.thegmd.assetjsonexample

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
Clase para leer el json
 *
 */
class JsonLeer(private val context: Context) {
    fun getData() =
        getDataFromAssets()

    private fun getDataFromAssets() : ModeloJson{
        val jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
        val gson = Gson()
        val listTipo = object : TypeToken<ModeloJson>() {}.type
        return gson.fromJson(jsonString,listTipo)
    }
}