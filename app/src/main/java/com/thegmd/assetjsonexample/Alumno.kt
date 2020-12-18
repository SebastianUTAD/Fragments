package com.thegmd.assetjsonexample

import com.google.gson.annotations.SerializedName

/**
 * Modelo Alumno
 */
data class Alumno(
    @SerializedName("codigo")
    val codigo: Int,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellido")
    val apellido: String,
    @SerializedName("Asignaturas")
    val asignatura: List<String>
)