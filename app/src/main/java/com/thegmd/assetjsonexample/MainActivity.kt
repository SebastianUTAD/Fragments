package com.thegmd.assetjsonexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.thegmd.assetjsonexample.databinding.ActivityMainBinding

/**
 * Se muestra a los estudiantes
 * Y los detalles si haces click
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i("GameFragment", "Called ViewModelProvider.get")
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.asisnaturas.observe(this, {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item, it
            )
            binding.spinnerSubjects.adapter = adapter
        })

        binding.spinnerSubjects.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, p2: Int, p3: Long) {
                if (view is TextView) {
                    viewModel.cambiarAsignatura(view.text.toString())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        viewModel.profesor.observe(this, {
            binding.textViewNombreProfesor.text = it.nombre + it.apellidos
            binding.textViewCodigoProfesor.text = it.codigo.toString()
        })

        val nameAdapter =
            AlumnoRecycle(mainCallBack = object : AlumnoRecycle.AlumnoCallbackListener {
                override fun onItemClicked(code: Int) {
                    if(binding.textViewStudentDataName != null){
                        val alumno = viewModel.getAlumno(code)
                        binding.textViewStudentDataCode?.text = alumno.codigo.toString()
                        binding.textViewStudentDataName?.text = alumno.nombre + alumno.apellido
                        binding.textViewStudentDataSubjects?.text = alumno.asignatura.joinToString(",")
                    }else{
                        val intent = Intent(this@MainActivity,DetailActivity::class.java).apply {
                            putExtra("STUDENT_CODE",code)
                        }
                        startActivity(intent)
                    }
                }
            })
        viewModel.subAlumnos.observe(this, {
            nameAdapter.alumnos = it
        })

        binding.listaAlumnos.adapter = nameAdapter
        binding.listaAlumnos.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}