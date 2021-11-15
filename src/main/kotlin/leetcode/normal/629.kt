package leetcode.normal

import utils.print
import kotlin.system.measureTimeMillis

fun main() {
    val s = Solution629()
//    s.kInversePairs(3, 0).print()
    s.kInversePairs(3, 1).print()
    measureTimeMillis {
        s.kInversePairs(1000, 1000).print()
    }.also {
        it.print()
    }
}

class Solution629 {
    fun kInversePairs(n: Int, k: Int): Int {
        val mod = 1000000007L
        val seen = LongArray(1001 * 1001) { -1L }

        fun dfs(index: Int, k: Int): Long {
            if (k == 0) return 1L
            if (k < 0) return 0L
            if (index == n) return 0L

            val key = index * 1000 + k
            if (seen[key] != -1L) return seen[key]

            var ans = 0L
            for (i in 0..minOf(n - index, k)) {
                ans += dfs(index + 1, k - i)
                ans %= mod
            }
            return ans.also {
                seen[key] = it
            }
        }
        return dfs(1, k).toInt()
    }
}