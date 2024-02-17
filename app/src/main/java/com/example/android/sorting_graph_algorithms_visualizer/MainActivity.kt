package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val objAnimation = Activity_Animation001( this)
        setContentView(objAnimation)

    }

}