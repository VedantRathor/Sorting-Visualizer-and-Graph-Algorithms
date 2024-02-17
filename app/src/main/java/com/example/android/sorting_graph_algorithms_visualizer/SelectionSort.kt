package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class SelectionSort : AppCompatActivity() {
    private lateinit var sortButton: Button
    private lateinit var resetButton: Button
    private lateinit var arrayView: ArrayView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection_sort)
        sortButton = findViewById(R.id.sort_button)
        resetButton = findViewById(R.id.reset_button)
        arrayView = findViewById(R.id.array_view)

        sortButton.setOnClickListener {
            Selection_sort()
        }

        resetButton.setOnClickListener {
            arrayView.reset()
        }
    }

    private fun Selection_sort() {
        val array = arrayView.getArray()

        Thread {
            for (i in array.indices) {
                var minIndex = i
                for (j in i + 1 until array.size) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j
                    }
                }
                val temp = array[minIndex]
                array[minIndex] = array[i]
                array[i] = temp

                arrayView.Invalidate(array) // Request a redraw on the UI thread
                Thread.sleep(100) // Delay to visualize the sorting process
            }
            arrayView.changeColor(array)
        }.start()
    }
}