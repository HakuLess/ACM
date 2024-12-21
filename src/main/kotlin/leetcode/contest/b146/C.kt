package leetcode.contest.b146

import utils.Interval
import utils.and
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.checkValidCuts(5, "[[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]".toGrid()).print()
    s.checkValidCuts(3, "[[0,0,1,3],[1,0,2,2],[2,0,3,2],[1,2,3,3]]".toGrid()).print()
}

class SolutionC {
    fun checkValidCuts(n: Int, rectangles: Array<IntArray>): Boolean {

        fun canCut(rectangles: Array<IntArray>, isHorizontal: Boolean): Boolean {
            val lines = mutableListOf<Pair<Int, Int>>()
            if (isHorizontal) {
                for (rect in rectangles) {
                    lines.add(rect[1] to rect[3])
                }
            } else {
                for (rect in rectangles) {
                    lines.add(rect[0] to rect[2])
                }
            }
            lines.sortWith(compareBy({ it.first }, { -it.second }))

//            lines.joinToString().print()

            val intervals = ArrayList<Interval>()
            intervals.add(Interval(left = lines[0].first, right = lines[0].second))
            var ans = 0
            for (i in 1 until lines.size) {
//                println("$i : ${intervals.joinToString(";")}")
                val item = Interval(lines[i].first, lines[i].second)
                if (item.left >= intervals.last().right) {
                    ans++
                    if (item.left == intervals.last().right) {
                        val lst = intervals.removeAt(0)
                        intervals.add((lst and item)!!)
                    } else {
                        intervals.add(item)
                    }
                } else {
                    val lst = intervals.removeAt(0)
//                    println("$lst $item & ${lst and item}")
                    intervals.add((lst and item)!!)
                }

                if (ans >= 2) return true
            }


            return false
        }

        if (canCut(rectangles, isHorizontal = true)) {
            return true
        }
        if (canCut(rectangles, isHorizontal = false)) {
            return true
        }
        return false
    }
}