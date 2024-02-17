package com.example.android.sorting_graph_algorithms_visualizer

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*

import kotlin.collections.ArrayList

class GraphView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var rededge = false
    var sx = -101f
    var sy = -101f
    var ex = -101f
    var ey = -101f
    var t = Paint().apply {
        color = Color.RED
    }


    private val nodeRadius = 50f
    private  lateinit var can : Canvas

    private val nodePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private val edgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 5f
    }

    private val shortestPathEdgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 8f
    }

    private val startNode = 0 // Starting node for BFS

    private lateinit var graph: Array<ArrayList<Int>> // Adjacency list representation of the graph

    // Define your graph data here
    private val nodes = arrayOf(
        floatArrayOf(100f, 100f),
        floatArrayOf(300f, 100f),
        floatArrayOf(200f, 300f),
        floatArrayOf(400f, 200f),
        floatArrayOf(400f, 500f),
        floatArrayOf(700f, 200f),
        floatArrayOf(800f, 400f),
        floatArrayOf(600f, 800f)
        // Add more nodes as needed
    )

    private val edges = arrayOf(
        intArrayOf(0, 1), // Edge between node 0 and node 1
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 0),
        intArrayOf(0, 2),
        intArrayOf(4,1),
        intArrayOf(5,4),
        intArrayOf(7,3),
        intArrayOf(6,7)
        // Add more edges as needed
    )

    init {
        initializeGraph()
    }

    private fun initializeGraph() {
        graph = Array(nodes.size) { ArrayList() }
        for (edge in edges) {
            val u = edge[0]
            val v = edge[1]
            graph[u].add(v)
            graph[v].add(u)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        can = canvas
        drawEdges(canvas)
        drawNodes(canvas)

    }

    private fun drawEdges(canvas: Canvas) {
        for (edge in edges) {
            val startNode = edge[0]
            val endNode = edge[1]

            val startX = nodes[startNode][0]
            val startY = nodes[startNode][1]
            val endX = nodes[endNode][0]
            val endY = nodes[endNode][1]
            if( rededge == true ){
                rededge = false
                android.os.Handler().postDelayed({
                    canvas.drawLine(sx, sy, ex, ey, shortestPathEdgePaint)
                }, 1000) // Delay of 1000 milliseconds (1 second)

                sx = -101f
                 sy = -101f
                 ex = -101f
                 ey = -101f
            }else {
                canvas.drawLine(startX, startY, endX, endY, edgePaint)
            }
        }
    }

    private fun drawNodes(canvas: Canvas) {
        for ((index, node) in nodes.withIndex()) {
            val x = node[0]
            val y = node[1]

            canvas.drawCircle(x, y, nodeRadius, nodePaint)
            val t = Paint().apply {
                color = Color.WHITE
            }
            canvas.drawText(index.toString(), x, y + nodeRadius / 2, t )
        }
    }

    fun findShortestPath(targetNode: Int) {
        val visited = BooleanArray(nodes.size)
        val queue: Queue<Int> = LinkedList()
        val parent = IntArray(nodes.size) { -1 }

        visited[startNode] = true
        queue.add(startNode)

        while (queue.isNotEmpty()) {
            val currentNode = queue.poll()

            for (adjNode in graph[currentNode]) {
                if (!visited[adjNode]) {
                    visited[adjNode] = true
                    queue.add(adjNode)
                    parent[adjNode] = currentNode
                }
            }
        }
//        if( done == true ){
//            invalidate()
//           done = false
//        }
        var currentNode = targetNode
        while (parent[currentNode] != -1) {
            val parentNode = parent[currentNode]
            val startX = nodes[currentNode][0]
            val startY = nodes[currentNode][1]
            val endX = nodes[parentNode][0]
            val endY = nodes[parentNode][1]

             sx = startX
            sy = startY
            ex = endX
            ey = endY
            rededge = true
             invalidate()
            currentNode = parentNode

        }

//        highlightShortestPath(targetNode, parent)
    }



//    private fun drawEdgeWithDelay(startX: Float, startY: Float, endX: Float, endY: Float) {
//        android.os.Handler().postDelayed({
//            drawEdgeWithPaint(can, startX, startY, endX, endY)
//        }, 1000) // Delay of 1000 milliseconds (1 second)
//    }
//
//    private fun drawEdgeWithPaint(
//        canvas: Canvas,
//        startX: Float,
//        startY: Float,
//        endX: Float,
//        endY: Float,
//    ) {
//        canvas.drawLine(startX, startY, endX, endY, shortestPathEdgePaint)
//        invalidate()
//    }
}
