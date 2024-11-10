package leetcode.contest.c423

import utils.print

fun main() {
    val s = SolutionC()
    // 14
    s.sumOfGoodSubsequences(intArrayOf(1, 2, 1)).print()
    // 40
    s.sumOfGoodSubsequences(intArrayOf(3, 4, 5)).print()
    // 155
    s.sumOfGoodSubsequences(intArrayOf(6, 7, 8, 7)).print()
}

class SolutionC {
    fun sumOfGoodSubsequences(nums: IntArray): Int {
        val mod = 1_000_000_007L

        val MAX = 100005
        val sum = LongArray(MAX)
        val cnt = LongArray(MAX)
        for (i in nums.indices.reversed()) {
            val v = nums[i]
            cnt[v]++

            val tmp = 1L + cnt[v + 1] + cnt.getOrElse(v - 1) { 0L }
            cnt[v] += cnt[v + 1]
            cnt[v] += cnt.getOrElse(v - 1) { 0L }

            sum[v] += v.toLong() * tmp

            sum[v] += sum[v + 1]
            sum[v] += sum.getOrElse(v - 1) { 0L }

            cnt[v] %= mod
            sum[v] %= mod
        }

        return (sum.sum() % mod).toInt()
    }
}