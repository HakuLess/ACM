package leetcode.contest.c297

import utils.print

fun main() {
    val s = SolutionC()
    s.distributeCookies(intArrayOf(8, 15, 10, 20, 8), 2).print()
}

class SolutionC {
    fun distributeCookies(cookies: IntArray, k: Int): Int {
        var ans = Int.MAX_VALUE
        fun dfs(i: Int, cur: IntArray) {
            if (i !in cookies.indices) {
                ans = minOf(ans, cur.maxOrNull()!!)
                return
            }
            for (t in 0 until k) {
                val c = cur.clone()
                c[t] += cookies[i]
                dfs(i + 1, c)
            }
        }
        dfs(0, IntArray(k))
        return ans
    }
}