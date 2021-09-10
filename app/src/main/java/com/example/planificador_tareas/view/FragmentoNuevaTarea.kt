package com.example.planificador_tareas.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.planificador_tareas.databinding.ActivityMainBinding
import com.example.planificador_tareas.databinding.FragmentFragmentoNuevaTareaBinding


class FragmentoNuevaTarea(val listener: (texto: String)->Unit) : DialogFragment() {

    private var _binding: FragmentFragmentoNuevaTareaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentFragmentoNuevaTareaBinding.inflate(LayoutInflater.from(context))
        binding.btnCrearTarea.setOnClickListener {
            listener(binding.etTituloNuevaTarea.text.toString())
            onDestroyView()
        }
        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams
    }



}