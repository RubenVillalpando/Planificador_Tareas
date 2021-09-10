package com.example.planificador_tareas.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.planificador_tareas.databinding.ActivityMainBinding
import com.example.planificador_tareas.databinding.FragmentFragmentoNuevaTareaBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var bindingFragmento: FragmentFragmentoNuevaTareaBinding
    lateinit var spTitulos: SharedPreferences
    lateinit var spCompletado: SharedPreferences
    lateinit var manejadorTarea: Tarea
    var sigId = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        spTitulos = getSharedPreferences("titulosTareas", Context.MODE_PRIVATE)
        spCompletado = getSharedPreferences("boolsCompletado", Context.MODE_PRIVATE)
        manejadorTarea = Tarea(spTitulos, spCompletado, this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingFragmento = FragmentFragmentoNuevaTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarTareas()
        registrarEventos()
    }

    private fun cargarTareas() {
        val tareas = spTitulos.all
        val checkboxes = spCompletado.all
        var maxId= 0
        for (llave in tareas.keys){
            maxId = llave.toInt()
            crearTarea(maxId, tareas.get(llave).toString(), checkboxes.get(llave) as Boolean )
        }
        sigId = maxId + 1
    }

    private fun registrarEventos() {
        binding.btnCrearTareaFragment.setOnClickListener {
            val nuevaTarea = FragmentoNuevaTarea{titulo->
                crearTarea(sigId, titulo, false)
            sigId++
        }
        nuevaTarea.show(supportFragmentManager, "creadorTarea")
        }
    }

    private fun crearTarea(id: Int, titulo: String, completado: Boolean) {
        val root: LinearLayout = binding.vlContenedorTarea
        val nuevaTarea = manejadorTarea.crearTarea(id, titulo, completado)
        root.addView(nuevaTarea)

    }

}