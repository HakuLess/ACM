package leetcode.contest.c418

import utils.print
import utils.printInt
import utils.toGraph
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionC()
    s.constructGridLayout(4, "[[0,1],[0,2],[1,3],[2,3]]".toGrid()).print()
    s.constructGridLayout(5, "[[0,1],[1,3],[2,3],[2,4]]".toGrid()).print()
    s.constructGridLayout(9, "[[0,1],[0,4],[0,5],[1,7],[2,3],[2,4],[2,5],[3,6],[4,6],[4,7],[6,8],[7,8]]".toGrid())
        .print()
}

class SolutionC {
    fun constructGridLayout(n: Int, edges: Array<IntArray>): Array<IntArray> {
        val g = edges.toGraph(n)
        val map = HashMap<Int, Int>()
        val mapV = HashMap<Int, ArrayList<Int>>()
        g.adj.forEachIndexed { index, it ->
            map[it.size] = map.getOrDefault(it.size, 0) + 1
            mapV[it.size] = mapV.getOrDefault(it.size, arrayListOf<Int>())
            mapV[it.size]!!.add(index)
        }
//        map.printInt()

//        mapV.forEach { t, u ->
//            println("$t : ${u.joinToString()}")
//        }

        // 一长条
        var row = 1
        var col = n
        if (1 !in map.keys) {
            row = map.getOrDefault(3, 0) / 4 + 2
            col = n / row
        }

//        println("row $row col $col")
        val ans = Array<IntArray>(row) { IntArray(col) }
        if (row == 1) {
            ans[0][0] = mapV[1]!!.first()
        } else {
            ans[0][0] = mapV[2]!!.first()
        }

        val queue: Queue<Int> = LinkedList<Int>()
        queue.offer(ans[0][0])
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                queue.offer(next)
            }
        }

        return ans
    }
}