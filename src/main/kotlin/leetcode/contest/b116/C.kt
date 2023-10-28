package leetcode.contest.b116

import utils.print

fun main() {
    val s = SolutionC()
    s.lengthOfLongestSubsequence(listOf(1, 2, 3, 4, 5), 9).print()
}

class SolutionC {
    fun lengthOfLongestSubsequence(nums: List<Int>, target: Int): Int {
        val seen = HashMap<Int, Int>()
        fun dfs(index: Int, left: Int): Int {
            val key = index * 2000 + left
            if (key in seen) return seen[key]!!
            if (left < 0) return -1
            if (left == 0) return 0
            if (index !in nums.indices) return -1
            var ans = -1
            ans = maxOf(ans, dfs(index + 1, left))
            ans = maxOf(ans, dfs(index + 1, left - nums[index]).let { if (it == -1) -1 else it + 1 })
            return ans.also {
                seen[key] = it
            }
        }
        return dfs(0, target)
    }
}