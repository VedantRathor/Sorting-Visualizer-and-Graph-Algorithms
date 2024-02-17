package com.example.android.sorting_graph_algorithms_visualizer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ArrayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {
    var setAllBlue = false
    private var array = intArrayOf(
        10, 20, 3, 3, 9, 10, 32, 32, 1, 33, 1, 6, 13
    ) // The array to visualize

    private val paint = Paint()

    fun updateArray(newArray: IntArray) {
        array = newArray
        invalidate() // Request a redraw
    }

    fun reset() {
        array = intArrayOf() // Reset the array
        invalidate() // Request a redraw
    }

    fun getArray(): IntArray {
        return array
    }

     fun changeColor(array: IntArray) {
        setAllBlue = true
        invalidate()
    }

    fun Invalidate( array : IntArray ){
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var flag = 1
        val width = width.toFloat()
        val height = height.toFloat()
        val textPaint = Paint().apply {
            color = Color.BLACK
        }
        val barWidth = (width / array.size) - 4f
        var l = 0f
        var k = 2f
        for (i in array.indices) {

            val left = i * barWidth + l
            val right = left + barWidth
            val top =
                height * (1 - array[i] / 100f) // Normalize array values to fit within view height
            val bottom = height
            if( setAllBlue == true ) {
                paint.color = Color.BLUE
                canvas.drawRect(left, top, right, bottom, paint)
            }else{
                val paintt = Paint().apply {
                    color = Color.RED
                }
                canvas.drawRect(left, top, right, bottom, paintt)
            }
            l = 10f + k
            k += 2f

            val text = array[i].toString()
            val textWidth = textPaint.measureText(text)
            textPaint.textSize = 30f
            val x = left + (barWidth - textWidth) / 2 // Center text horizontally above the bar
            val y = top - 20 // Position text slightly above the bar
            canvas.drawText(text, x, y, textPaint)

        }
    }
}