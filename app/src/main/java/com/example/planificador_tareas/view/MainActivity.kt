package com.example.planificador_tareas.view

import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.planificador_tareas.databinding.ActivityMainBinding
import com.example.planificador_tareas.databinding.FragmentFragmentoNuevaTareaBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var bindingFragmento: FragmentFragmentoNuevaTareaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        bindingFragmento = FragmentFragmentoNuevaTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registrarEventos()
    }

    private fun registrarEventos() {
        binding.btnCrearTareaFragment.setOnClickListener {
            val nuevaTarea = FragmentoNuevaTarea{titulo->
                crearTarea(titulo)}
            nuevaTarea.show(supportFragmentManager, "creadorTarea")


        }
    }

    private fun crearTarea(titulo: String) {
        val root: ScrollView = binding.svContenedorTareas

        //crear checkbox
        val chkbox: CheckBox = CheckBox(this)


        //crear textview
        val tituloTarea: TextView = TextView(this)
        tituloTarea.setText(titulo)
        tituloTarea.width = 300
        tituloTarea.gravity = Gravity.LEFT
        tituloTarea.textSize = 20.0f


        //crear boton eliminar
        val botonEliminar: ImageButton = ImageButton(this)
        botonEliminar.setImageResource(android.R.drawable.ic_menu_delete)

        //crear el linearLayout
        val nuevaTarea: LinearLayout = LinearLayout(this)
        nuevaTarea.orientation = LinearLayout.HORIZONTAL
        nuevaTarea.gravity = Gravity.LEFT

        nuevaTarea.addView(chkbox)
        nuevaTarea.addView(tituloTarea)
        nuevaTarea.addView(botonEliminar)
        root.addView(nuevaTarea)

    }

}