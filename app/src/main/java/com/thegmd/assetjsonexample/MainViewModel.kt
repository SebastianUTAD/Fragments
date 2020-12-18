package com.thegmd.assetjsonexample

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * Muestra datos
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val jsonLeer: JsonLeer = JsonLeer(application)

    private val _asig = MutableLiveData<ArrayList<String>>()
    val asisnaturas: LiveData<ArrayList<String>>
        get() = _asig

    private val profesores = ArrayList<Profesor>()

    private val _profe = MutableLiveData<Profesor>()
    val profesor: LiveData<Profesor>
        get() = _profe

    private val alumnos = ArrayList<Alumno>()

    private val _subAlumnos = MutableLiveData<List<Alumno>>()
    val subAlumnos: LiveData<List<Alumno>>
        get() = _subAlumnos

    init {
        Log.i("MainViewModel", "MainViewModel created!")
        val res = jsonLeer.getData()
        _asig.postValue(
            res.asignaturas
        )
        profesores.addAll(
            res.profesores
        )
        alumnos.addAll(
            res.alumnos
        )
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("MainViewModel", "MainViewModel destroyed!")
    }

    fun cambiarAsignatura(nombreAsignatura: String) {
        val profesorAsignatura = profesores.filter { it.asignatura == nombreAsignatura }
        _profe.postValue(profesorAsignatura.first())

        val alumnosAsignatura = alumnos.filter { it.asignatura.any { sName -> sName == nombreAsignatura } }
        _subAlumnos.postValue(alumnosAsignatura)
    }

    fun getAlumno(codigo: Int): Alumno {
        return alumnos.first { it.codigo == codigo }
    }
}