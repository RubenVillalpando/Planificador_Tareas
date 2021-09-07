package com.example.planificador_tareas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.planificador_tareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        var mainView = mainBinding.root

        setContentView(mainView)


    }
}