package com.example.planificador_tareas.view

import android.content.Context
import android.content.SharedPreferences
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView


class Tarea(spTitulos: SharedPreferences, spCompletado: SharedPreferences, context:Context) {


    val editorTitulos = spTitulos.edit()
    val editorCompletado = spCompletado.edit()
    val context = context

    private fun guardarTarea(id: Int, titulo: String, completado: Boolean) {
        editorTitulos.putString(id.toString(), titulo)
        editorTitulos.commit()
        editorCompletado.putBoolean(id.toString(), completado)
        editorCompletado.commit()
    }

    private fun editarGuardado(id: Int, checked: Boolean) {
        editorCompletado.putBoolean(id.toString(), checked)
        editorCompletado.commit()
    }

    private fun quitarTarea(id: Int){
        editorTitulos.remove(id.toString())
        editorTitulos.commit()
        editorCompletado.remove(id.toString())
        editorCompletado.commit()
    }

    fun crearTarea(id: Int, titulo: String, completado: Boolean): LinearLayout{

        if (titulo.isEmpty()) throw(NullPointerException())

        context.resources.displayMetrics

        //crear checkbox
        val chkbox= CheckBox(context)
        chkbox.width = 48.toPx(context)
        chkbox.height = 48.toPx(context)
        chkbox.isChecked = completado


        //crear textview
        val tituloTarea= TextView(context)
        tituloTarea.setText(titulo)
        tituloTarea.width = 300.toPx(context)
        tituloTarea.gravity = Gravity.LEFT
        tituloTarea.textSize = 20.0f

        //crear boton eliminar
        val botonEliminar= ImageButton(context)
        botonEliminar.setImageResource(android.R.drawable.ic_menu_delete)

        //crear el linearLayout
        val nuevaTarea = LinearLayout(context)
        nuevaTarea.id = id
        nuevaTarea.orientation = LinearLayout.HORIZONTAL
        nuevaTarea.gravity = Gravity.LEFT

        botonEliminar.setOnClickListener {
            nuevaTarea.visibility = View.GONE
            quitarTarea(nuevaTarea.id)
        }

        chkbox.setOnClickListener {
            editarGuardado(nuevaTarea.id, chkbox.isChecked)
        }

        nuevaTarea.addView(chkbox)
        nuevaTarea.addView(tituloTarea)
        nuevaTarea.addView(botonEliminar)

        guardarTarea(nuevaTarea.id, tituloTarea.text.toString(), chkbox.isChecked)

        return nuevaTarea
    }



    private fun Int.toPx(context: Context) = this * context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT

}