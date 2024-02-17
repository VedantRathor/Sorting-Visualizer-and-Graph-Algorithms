package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class InsertionSort : AppCompatActivity() {
    private lateinit var sortButton: Button
    private lateinit var resetButton: Button
    private lateinit var arrayView: ArrayView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insertion_sort)
        sortButton = findViewById(R.id.sort_button)
        resetButton = findViewById(R.id.reset_button)
        arrayView = findViewById(R.id.array_view)

        sortButton.setOnClickListener {
            Insertion_sort()
        }

        resetButton.setOnClickListener {
            arrayView.reset()
        }
    }

    private fun Insertion_sort() {
        val array = arrayView.getArray()

        Thread {
            for (i in 1 until array.size) {
                val key = array[i]
                var j = i - 1

                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j]
                    j--
                }
                array[j + 1] = key

                arrayView.Invalidate( array) // Request a redraw on the UI thread
                Thread.sleep(100) // Delay to visualize the sorting process
            }
            arrayView.changeColor(array)
        }.start()
    }
}