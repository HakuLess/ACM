package leetcode.contest.c326

import utils.print
import utils.printInt

fun main() {
    val s = SolutionC()
    s.minimumPartition("165462", 60).print()
    s.minimumPartition("75734379996162298577", 7).print()
}

class SolutionC {
    fun minimumPartition(s: String, k: Int): Int {
        val seen = HashMap<Int, Int>()
        fun dfs(index: Int): Int {
            if (index == s.length) return 0
            if (index in seen) return seen[index]!!
            var ans = Int.MAX_VALUE / 2
            for (i in minOf(index + 9, s.length) downTo index + 1) {
                val tmp = s.substring(index, i)
                if (tmp.toInt() <= k) {
                    ans = minOf(ans, dfs(i) + 1)
                }
            }
            return ans.also {
                seen[index] = it
            }
        }
        dfs(0)
        if (seen[0]!! >= Int.MAX_VALUE / 2) {
            return -1
        }
        return seen[0]!!
    }
}