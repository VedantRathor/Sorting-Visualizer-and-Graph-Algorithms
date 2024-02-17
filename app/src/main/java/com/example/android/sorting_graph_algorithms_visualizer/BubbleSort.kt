package com.example.android.sorting_graph_algorithms_visualizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class BubbleSort : AppCompatActivity() {
    private lateinit var sortButton: Button
    private lateinit var resetButton: Button
    private lateinit var arrayView: ArrayView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bubble_sort)

        sortButton = findViewById(R.id.sort_button)
        resetButton = findViewById(R.id.reset_button)
        arrayView = findViewById(R.id.array_view)

        sortButton.setOnClickListener {
            bubbleSort()
        }

        resetButton.setOnClickListener {
            arrayView.reset()
        }
    }

    private fun bubbleSort() {
        val array = arrayView.getArray()

        Thread {
            for (i in 0 until array.size - 1) {
                for (j in 0 until array.size - i - 1) {
                    if (array[j] > array[j + 1]) {
                        swap(array, j, j + 1)
                        runOnUiThread {
                            arrayView.updateArray(array)
                        }
                        Thread.sleep(1000) // Delay to visualize the sorting process
                    }
                }

            }
            arrayView.changeColor(array)
        }.start()
    }

    private fun swap(array: IntArray, i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }
}