package leetcode.contest.c304

import utils.print

fun main() {
    val s = SolutionD()
    s.longestCycle(intArrayOf(3, 3, 4, 2, 3)).print()
    s.longestCycle(intArrayOf(2, -1, 3, 1)).print()
    // -1
    s.longestCycle(intArrayOf(-1, 4, -1, 2, 0, 4)).print()
}

class SolutionD {
    fun longestCycle(edges: IntArray): Int {
        val n = edges.size
        val seen = HashSet<Int>()
        var ans = -1

        fun dfs(i: Int, arr: ArrayList<Int> = arrayListOf()) {
            if (i == -1) {
                return
            }
            if (i in seen) {
                if (i in arr) {
                    ans = maxOf(ans, arr.size - arr.indexOf(i))
                }
                return
            }
            seen.add(i)
            arr.add(i)
            dfs(edges[i], arr)
        }

        for (i in 0 until n) {
            dfs(i)
        }
        if (ans <= 1) return -1
        return ans
    }
}