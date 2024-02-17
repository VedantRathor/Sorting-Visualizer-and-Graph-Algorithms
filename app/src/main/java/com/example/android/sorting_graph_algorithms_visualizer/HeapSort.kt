package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HeapSort : AppCompatActivity() {
    private lateinit var sortButton: Button
    private lateinit var resetButton: Button
    private lateinit var arrayView: ArrayView
    private lateinit var array: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heap_sort)
        sortButton = findViewById(R.id.sort_button)
        resetButton = findViewById(R.id.reset_button)
        arrayView = findViewById(R.id.array_view)
         array = arrayView.getArray()
        sortButton.setOnClickListener {
            Heap_sort()
        }

        resetButton.setOnClickListener {
            arrayView.reset()
        }
    }

    private fun Heap_sort() {


        Thread {
            val n = array.size
            // Build heap (rearrange array)
            for (i in n / 2 - 1 downTo 0) {
                heapify(n, i)
            }
            // One by one extract an element from heap
            for (i in n - 1 downTo 0) {
                // Move current root to end
                val temp = array[0]
                array[0] = array[i]
                array[i] = temp
                // call max heapify on the reduced heap
                heapify(i, 0)
                arrayView.Invalidate(array) // Request a redraw on the UI thread
                Thread.sleep(400) // Delay to visualize the sorting process
            }
            arrayView.changeColor(array)
        }.start()
    }

    private fun heapify(n: Int, i: Int) {
        var largest = i // Initialize largest as root
        val left = 2 * i + 1 // left = 2*i + 1
        val right = 2 * i + 2 // right = 2*i + 2
        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left
        }
        // If right child is larger than largest so far
        if (right < n && array[right] > array[largest]) {
            largest = right
        }
        // If largest is not root
        if (largest != i) {
            val swap = array[i]
            array[i] = array[largest]
            array[largest] = swap
            // Recursively heapify the affected sub-tree
            heapify(n, largest)
        }
    }
}