package leetcode.contest.b131

import utils.print
import utils.printInt
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.queryResults(4, "[[1,4],[2,5],[1,3],[3,4]]".toGrid()).print()
}

class SolutionC {
    fun queryResults(limit: Int, queries: Array<IntArray>): IntArray {
        val ans = ArrayList<Int>()
        val map = HashMap<Int, Int>()
        val mapColor = HashMap<Int, Int>()
        var cur = 0
        queries.forEach {
//            println("enter ${it.joinToString()}")
            val (x, y) = it
            // 之前的颜色
            val a = map.getOrDefault(x, -1)

            // 之前颜色的数量
            val preColorCnt = mapColor.getOrDefault(a, -1)

            // 颜色的数量
            val b = mapColor.getOrDefault(y, -1)

//            map.printInt()
//            mapColor.printInt()
//            println("${it.joinToString()} with $cur  $a $b")

            if (a == y) {
                ans.add(cur)
                return@forEach
            }

            if (preColorCnt != -1) {
                if (preColorCnt == 1) {
                    mapColor.remove(a)
                    cur--
                } else {
                    mapColor[a] = mapColor[a]!! - 1
                }
            }

            if (b == -1) {
                cur++
                mapColor[y] = 1
            } else {
                mapColor[y] = mapColor[y]!! + 1
            }

            map[x] = y
            ans.add(cur)
        }
        return ans.toIntArray()
    }
}