package leetcode.contest.b116

import utils.print

fun main() {
    val s = SolutionA()
    s.sumCounts(listOf(1, 2, 1)).print()
    s.sumCounts(listOf(1, 1, 2)).print()
    s.sumCounts(listOf(1, 1, 1)).print()
    s.sumCounts(listOf(1, 2, 3)).print()
    s.sumCounts(listOf(2, 2)).print()
    s.sumCounts(listOf(1, 2, 3, 4)).print()
}

class SolutionA {
    fun sumCounts(nums: List<Int>): Int {
        var ans = 0L
        val mod = 1000000007L
        for (i in nums.indices) {
            val set = HashSet<Int>()
            for (j in i until nums.size) {
                set.add(nums[j])
                ans += set.size.toLong() * set.size.toLong()
                ans %= mod
            }
        }
        return (ans % mod).toInt()
    }
}