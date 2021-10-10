package leetcode.contest.c262

import utils.C
import utils.print
import utils.swap
import kotlin.math.abs
import kotlin.math.exp

fun main() {
    val s = Solution5897()
    s.minimumDifference(intArrayOf(3, 9, 7, 3)).print()
//    s.minimumDifference(intArrayOf(2, -1, 0, 4, -2, -9)).print()
//
//    s.minimumDifference(intArrayOf(0, 6, 11, 17, 18, 24)).print()
//    s.minimumDifference(intArrayOf(76, 8, 45, 20, 74, 84, 28, 1)).print()
}

class Solution5897 {
    // TODO 折半法
    // size为30的，先左15 右15拆
    fun minimumDifference(nums: IntArray): Int {
        val sum = nums.sum()
        var ans = Int.MAX_VALUE
        fun dfs(i: Int, cur: Int, total: Int) {
            if (cur == nums.size / 2) {
                ans = minOf(ans, abs(sum - total - total))
                return
            }
            if (i !in nums.indices) return
            dfs(i + 1, cur + 1, total + nums[i])
            dfs(i + 1, cur, total)
        }
        dfs(1, 1, nums[0])
        return ans
    }
}