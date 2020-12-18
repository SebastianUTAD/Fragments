package com.thegmd.assetjsonexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.thegmd.assetjsonexample.databinding.ActivityDetailBinding

/**
 *  Detalles estudiante
 */
class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val codigoAlumno = intent.getIntExtra("STUDENT_CODE",0)
        val alumno = viewModel.getAlumno(codigoAlumno)
        binding.textViewStudentDataCode.text = alumno.codigo.toString()
        binding.textViewStudentDataName.text = alumno.nombre + alumno.apellido
        binding.textViewStudentDataSubjects.text = alumno.asignatura.joinToString(",")

    }
}