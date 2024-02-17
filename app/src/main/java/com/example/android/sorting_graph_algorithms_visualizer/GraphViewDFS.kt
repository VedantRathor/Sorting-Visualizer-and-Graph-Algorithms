package com.example.android.sorting_graph_algorithms_visualizer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import java.util.*

class GraphViewDFS(context: Context, attrs: AttributeSet) : View(context, attrs) {

    var rededge = false
    var sx = -101f
    var sy = -101f
    var ex = -101f
    var ey = -101f
    private val nodeRadius = 50f
    private val nodePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLUE
        style = Paint.Style.FILL
    }

    private val edgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeWidth = 5f
    }

    private val visitedEdgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 20f
    }

    private lateinit var graph: Array<ArrayList<Int>> // Adjacency list representation of the graph

    // Define your graph data here
    private val nodes = arrayOf(
        floatArrayOf(100f, 100f),
        floatArrayOf(300f, 100f),
        floatArrayOf(200f, 300f),
        floatArrayOf(400f, 200f),
        // Add more nodes as needed
    )

    private val edges = arrayOf(
        intArrayOf(0, 1), // Edge between node 0 and node 1
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 0),
        intArrayOf(0, 2)
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
            graph[v].add(u) // Uncomment if the graph is undirected
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (edge in edges) {
            val startNode = edge[0]
            val endNode = edge[1]

            val startX = nodes[startNode][0]
            val startY = nodes[startNode][1]
            val endX = nodes[endNode][0]
            val endY = nodes[endNode][1]
            if( rededge == true && sx == startX && sy == startY && ex == endX && ey == endY   ){
                canvas.drawLine(sx, sy, ex, ey, visitedEdgePaint)
                rededge = false
            }else{
                canvas.drawLine(startX, startY, endX, endY, edgePaint)
            }
        }
        for ((index, node) in nodes.withIndex()) {
            val x = node[0]
            val y = node[1]
            canvas.drawCircle(x, y, nodeRadius, nodePaint)
            canvas.drawText(index.toString(), x, y + nodeRadius / 2, nodePaint)
        }

    }

    fun highlightDFS() {
        val visited = BooleanArray(nodes.size)
        val handler = Handler()

        // Start DFS from node 0
        dfs(0, visited, handler)
    }

    private fun dfs(node: Int, visited: BooleanArray, handler: Handler) {
        visited[node] = true

        for (adjNode in graph[node]) {
            if (!visited[adjNode]) {
                // Draw edge as visited after 1 second delay
                handler.postDelayed({
                    val startX = nodes[node][0]
                    val startY = nodes[node][1]
                    val endX = nodes[adjNode][0]
                    val endY = nodes[adjNode][1]
                    drawVisitedEdge(startX, startY, endX, endY)
                }, 1000)

                dfs(adjNode, visited, handler)
            }
        }
    }

    private fun drawVisitedEdge(startX: Float, startY: Float, endX: Float, endY: Float) {

        var rededge = true
       sx = startX
         sy = startY
        ex = endX
       ey = endY
        invalidate()
    }
}




//    private fun drawEdges(canvas: Canvas) {
//        for (edge in edges) {
//            val startNode = edge[0]
//            val endNode = edge[1]
//
//            val startX = nodes[startNode][0]
//            val startY = nodes[startNode][1]
//            val endX = nodes[endNode][0]
//            val endY = nodes[endNode][1]
//            if( rededge == true && sx == startX && sy == startY && ex == endX && ey == endY   ){
//                canvas.drawLine(sx, sy, ex, ey, visitedEdgePaint)
//                rededge = false
//            }else{
//            canvas.drawLine(startX, startY, endX, endY, edgePaint)
//            }
//        }
//    }
//
//    private fun drawNodes(canvas: Canvas) {
//        for ((index, node) in nodes.withIndex()) {
//            val x = node[0]
//            val y = node[1]
//            canvas.drawCircle(x, y, nodeRadius, nodePaint)
//            canvas.drawText(index.toString(), x, y + nodeRadius / 2, nodePaint)
//        }
//    }
