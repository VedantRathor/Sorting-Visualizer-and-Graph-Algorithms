package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MergeSort : AppCompatActivity() {
    private lateinit var sortButton: Button
    private lateinit var resetButton: Button
    private lateinit var arrayView: ArrayView
    private lateinit var array: IntArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merge_sort)

        sortButton = findViewById(R.id.sort_button)
        resetButton = findViewById(R.id.reset_button)
        arrayView = findViewById(R.id.array_view)
        array = arrayView.getArray()
        sortButton.setOnClickListener {
            Merge_sort()
        }

        resetButton.setOnClickListener {
            arrayView.reset()
        }

    }

    private fun Merge_sort(){
        Thread {
            mergeSort(array, 0, array.size - 1)
            arrayView.changeColor(array)
        }.start()
    }

    private fun mergeSort(arr: IntArray, l: Int, r: Int) {
        if (l < r) {
            val m = (l + r) / 2
            mergeSort(arr, l, m)
            mergeSort(arr, m + 1, r)
            merge(arr, l, m, r)
            arrayView.Invalidate(array) // Request a redraw on the UI thread
            Thread.sleep(300) // Delay to visualize the sorting process
        }
    }

    private fun merge(arr: IntArray, l: Int, m: Int, r: Int) {
        val n1 = m - l + 1
        val n2 = r - m
        val L = IntArray(n1)
        val R = IntArray(n2)
        for (i in 0 until n1) {
            L[i] = arr[l + i]
        }
        for (j in 0 until n2) {
            R[j] = arr[m + 1 + j]
        }
        var i = 0
        var j = 0
        var k = l
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i]
                i++
            } else {
                arr[k] = R[j]
                j++
            }
            k++
        }
        while (i < n1) {
            arr[k] = L[i]
            i++
            k++
        }
        while (j < n2) {
            arr[k] = R[j]
            j++
            k++
        }
    }
}