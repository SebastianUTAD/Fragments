package com.thegmd.assetjsonexample

import com.google.gson.annotations.SerializedName

/**
 * Mdelo para el json
 */
data class ModeloJson(
    @SerializedName("asignaturas")
    val asignaturas : ArrayList<String>,
    @SerializedName("profesores")
    val profesores : ArrayList<Profesor>,
    @SerializedName("alumnos")
    val alumnos : ArrayList<Alumno>
)