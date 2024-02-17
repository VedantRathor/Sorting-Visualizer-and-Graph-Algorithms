package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DFS : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dfs)
        val graphView = findViewById<GraphViewDFS>(R.id.graph_view2)
        val btn = findViewById<Button>(R.id.btn_sort)

        btn.setOnClickListener {
            graphView.highlightDFS() // Example: find shortest path to node 3
        }
    }
}