package com.thegmd.assetjsonexample

import com.google.gson.annotations.SerializedName

/**
 * Modelo profe
 */
data class Profesor(
    @SerializedName("codigo")
    val codigo : Int,
    @SerializedName("nombre")
    val nombre : String,
    @SerializedName("apellido")
    val apellidos : String,
    @SerializedName("asignatura")
    val asignatura : String
)