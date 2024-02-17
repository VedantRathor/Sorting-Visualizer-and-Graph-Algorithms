package com.example.android.sorting_graph_algorithms_visualizer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Home : AppCompatActivity() {

    private lateinit var btnBsort: Button
    private lateinit var btnIsort: Button
    private lateinit var btnSsort: Button
    private lateinit var btnHsort: Button
    private lateinit var btnMsort: Button
    private lateinit var btnQsort: Button
    private lateinit var btnbfs: Button
    private lateinit var btndfs: Button

    private fun inIt() {
        btnBsort = findViewById(R.id.btn_bubblesort)
        btnIsort = findViewById(R.id.btn_insertionsort)
        btnSsort = findViewById(R.id.btn_selectionsort)
        btnHsort = findViewById(R.id.btn_Heapsort)
        btnMsort = findViewById(R.id.btn_Mergesort)
        btnQsort = findViewById(R.id.btn_Quicksort)
        btnbfs = findViewById(R.id.btn_shortestPath)
        btndfs = findViewById(R.id.btn_dfsTraversal)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        inIt()

        btnBsort.setOnClickListener {
            startActivity(Intent(this, BubbleSort::class.java))
        }

        btnIsort.setOnClickListener {
            startActivity(Intent(this, InsertionSort::class.java))
        }

        btnSsort.setOnClickListener {
            startActivity(Intent(this, SelectionSort::class.java))
        }

        btnQsort.setOnClickListener {
            startActivity(Intent(this, QuickSort::class.java))
        }

        btnHsort.setOnClickListener {
            startActivity(Intent(this, HeapSort::class.java))
        }

        btnMsort.setOnClickListener {
            startActivity(Intent(this, MergeSort::class.java))
        }

        btnbfs.setOnClickListener {
            startActivity(Intent(this, BFS::class.java))
        }
        btndfs.setOnClickListener {
            startActivity(Intent(this, DFS::class.java))
        }

    }
}