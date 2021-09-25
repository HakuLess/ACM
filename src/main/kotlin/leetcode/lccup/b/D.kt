package leetcode.lccup.b

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionD()
//    s.securityCheck(intArrayOf(2, 2, 3), 2).print()
    s.securityCheck(intArrayOf(4, 3, 2, 2), 6).print()
    s.securityCheck(intArrayOf(3, 3), 3).print()
}

class SolutionD {
    fun securityCheck(capacities: IntArray, k: Int): Int {
        val mod = 1000000007L
        val arr = capacities.map { it - 1 }.filter { it != 0 }
        val cnt = capacities.count { it == 1 }
        val seen = HashMap<Pair<Int, Int>, Long>()
        fun dfs(cur: Int, index: Int): Long {
            val key = Pair(cur, index)
            if (key in seen) return seen[key]!!
            if (cur == k) return 1L
            if (index !in arr.indices || cur > k) return 0L

            var ans = 0L
            ans += dfs(cur + arr[index], index + 1)
            ans %= mod
            ans += dfs(cur, index + 1)
            ans %= mod
            return ans.also {
                seen[key] = it % mod
            }
        }
        return ((dfs(0, 0) * quickPower(2L, cnt.toLong(), mod)) % mod).toInt()
    }
}