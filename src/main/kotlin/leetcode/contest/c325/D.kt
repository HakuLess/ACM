package leetcode.contest.c325

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionD()
    // 6
    s.countPartitions(intArrayOf(1, 2, 3, 4), 4).print()
    // 524152
    s.countPartitions(intArrayOf(73, 16, 86, 25, 98, 92, 15, 11, 87, 88, 88, 94, 83, 74, 1, 48, 91, 9, 45), 61).print()
}

class SolutionD {
    fun countPartitions(nums: IntArray, k: Int): Int {
        val mod = 1000000007L
        var sum = 0L
        nums.forEach {
            sum += it
        }
        if (sum < 2 * k) return 0

        val seen = HashMap<String, Long>()
        fun dfs(i: Int = 0, cur: Int = 0): Long {
            val key = "$i, $cur"
            if (key in seen) return seen[key]!!
            if (i !in nums.indices) return 1L
            var ans = dfs(i + 1, cur)
            if (cur + nums[i] < k) {
                ans += dfs(i + 1, cur + nums[i])
                ans %= mod
            }
            return ans.also {
                seen[key] = it % mod
            }
        }
        return ((quickPower(2L, nums.size.toLong(), mod) - 2 * dfs()) % mod).toInt()
    }
}