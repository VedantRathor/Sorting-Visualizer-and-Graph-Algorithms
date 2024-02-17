package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class QuickSort : AppCompatActivity() {

    private lateinit var sortButton: Button
    private lateinit var resetButton: Button
    private lateinit var arrayView: ArrayView
    private lateinit var array: IntArray


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quick_sort)


        sortButton = findViewById(R.id.sort_button)
        resetButton = findViewById(R.id.reset_button)
        arrayView = findViewById(R.id.array_view)
        array = arrayView.getArray()
        sortButton.setOnClickListener {
            Quick_sort()
        }

        resetButton.setOnClickListener {
            arrayView.reset()
        }

    }

    private fun Quick_sort(){
        Thread {
            quickSort(array, 0, array.size - 1)
            arrayView.changeColor(array)
        }.start()
    }
    private fun quickSort(arr: IntArray, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(arr, low, high)
            arrayView.Invalidate(array) // Request a redraw on the UI thread
            Thread.sleep(300) // Delay to visualize the sorting process
            quickSort(arr, low, pi - 1)
            quickSort(arr, pi + 1, high)
        }
    }
    private fun partition(arr: IntArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low until high) {
            if (arr[j] < pivot) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }
        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        return i + 1
    }
}