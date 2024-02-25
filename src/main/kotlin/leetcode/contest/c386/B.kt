package leetcode.contest.c386

import utils.Rect
import utils.and
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.largestSquareArea("[[1,1],[2,2],[3,1]]".toGrid(), "[[3,3],[4,4],[6,6]]".toGrid()).print()
}

class SolutionB {
    fun largestSquareArea(bottomLeft: Array<IntArray>, topRight: Array<IntArray>): Long {
        val l = ArrayList<Rect>()
        for (i in bottomLeft.indices) {
            val bl = bottomLeft[i]
            val tr = topRight[i]
            l.add(Rect(bl[0].toDouble(), tr[0].toDouble(), bl[1].toDouble(), tr[1].toDouble()))
        }

        var ans = 0L
        for (i in l.indices) {
            for (j in i + 1 until l.size) {
                val a = l[i]
                val b = l[j]
//                println("$i $j : $a, $b")
                val c = (a and b) ?: continue

                val edge = minOf(c.y1 - c.y0, c.x1 - c.x0)
                ans = maxOf(ans, edge.toLong() * edge.toLong())
            }
        }
        return ans
    }
}