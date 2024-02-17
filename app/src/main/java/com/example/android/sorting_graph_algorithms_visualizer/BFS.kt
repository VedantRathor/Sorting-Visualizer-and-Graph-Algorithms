package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import java.util.logging.Handler

class BFS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bfs)
        val graphView = findViewById<GraphView>(R.id.graph_view)
        val btn = findViewById<Button>(R.id.btn_sort)

        btn.setOnClickListener {
            graphView.findShortestPath(3) // Example: find shortest path to node 3
        }


    }
}