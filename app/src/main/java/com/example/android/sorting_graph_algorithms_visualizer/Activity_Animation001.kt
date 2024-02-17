package com.example.android.sorting_graph_algorithms_visualizer

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.Style
import android.graphics.Path
import android.view.View


class Activity_Animation001(
    context : Context
) : View(context){
    private lateinit var redPaint : Paint
    private lateinit var bluePaint : Paint
    private lateinit var tmpcolor : Paint
    private lateinit var bitmap : Bitmap
    var x_direct = -3f
    var y_direct = -3f
    var bitmap_x = 550f
    var bitmap_y = 1800f



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        redPaint = Paint()
        redPaint.color = Color.RED
        redPaint.style = Paint.Style.FILL

        bluePaint = Paint()
        bluePaint.color = Color.BLUE

        tmpcolor = Paint()
        tmpcolor.color = Color.GREEN

        // Draw a rectangle with proper coordinates
//        canvas.drawRect(0f, 0f, 300f, 300f, redPaint)
        canvas.drawCircle(550f,700f, 200f , redPaint)
        val triangle = Path()
        triangle.moveTo(550f, 700f)
        triangle.lineTo(200f, 1400f)
        triangle.lineTo(950f, 1400f)
        triangle.close() // Close the path to complete the triangle
        canvas.drawPath(triangle, bluePaint)
        canvas.drawCircle(550f,700f, 60f , tmpcolor )
        canvas.drawCircle(200f,1400f, 60f , tmpcolor)
        canvas.drawCircle(950f,1400f, 60f , tmpcolor)

        bitmap = BitmapFactory.decodeResource(resources,R.drawable.btiamp)

        if( bitmap_x <= 0   ){
            x_direct += 3f
        }
        if( bitmap_y <= 0 ){
            y_direct += 3
        }
        if( bitmap_x >= width ){
            x_direct -= 3
        }
        if( bitmap_y >= height ){
            y_direct -= 3
        }

       bitmap_x += x_direct
        bitmap_y += y_direct
        canvas.drawBitmap(bitmap,bitmap_x,bitmap_y,bluePaint)

        invalidate()
    }

}