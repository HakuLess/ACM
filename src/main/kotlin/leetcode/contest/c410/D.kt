package leetcode.contest.c410

import utils.print

fun main() {
    val s = SolutionD()
    s.countOfPairs(intArrayOf(2, 3, 2)).print()
    s.countOfPairs(intArrayOf(5, 5, 5, 5)).print()
//    val arr = ArrayList<Int>()
//    repeat(300) {
//        arr.add(1000)
//    }
//    arr.joinToString(",").print()
//    s.countOfPairs(arr.toIntArray()).print()
}

class SolutionD {
    fun countOfPairs(nums: IntArray): Int {
        val n = nums.size
        val mod = 1000000007L
        val maxNum = nums.maxOrNull()!!

        var dp = LongArray(maxNum + 1) { 1L }

        for (i in 1 until n) {
            val pre = LongArray(maxNum + 1)
            pre[0] = dp[0]
            for (j in 1..maxNum) {
                pre[j] = (pre[j - 1] + dp[j]) % mod
            }

            val newDp = LongArray(maxNum + 1) { 0L }
            for (j in 0..nums[i]) {
                // 数组2保持非递增
                val r = minOf(j, nums[i - 1] - (nums[i] - j))
                if (r >= 0) {
                    newDp[j] = pre[r]
                }
            }
            dp = newDp
        }

        var ans = 0L
        dp.forEach {
            ans += it
            ans %= mod
        }
        return (ans % mod).toInt()
    }
}