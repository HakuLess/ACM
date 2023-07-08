package leetcode.contest.b108

import utils.dir4lp
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.countBlackBlocks(3, 3, "[[0,0]]".toGrid()).print()
    s.countBlackBlocks(3, 3, "[[0,0],[1,1],[0,2]]".toGrid()).print()
}

class SolutionD {
    fun countBlackBlocks(m: Int, n: Int, coordinates: Array<IntArray>): LongArray {
        val r = 1000000L
        val all = (m - 1).toLong() * (n - 1).toLong()
        val ans = LongArray(5) { 0L }

        val map = HashMap<Long, Int>()

        fun check(x: Int, y: Int) {
            if (x in 0 until m - 1 && y in 0 until n - 1) {
                val key = x + y * r
                map[key] = map.getOrDefault(key, 0) + 1
            }
        }

        for ((x, y) in coordinates) {
            dir4lp.forEach {
                check(x + it[0], y + it[1])
            }
        }

        map.keys.forEach {
            ans[map[it]!!]++
        }
        ans[0] = all - ans[1] - ans[2] - ans[3] - ans[4]
        return ans
    }
}