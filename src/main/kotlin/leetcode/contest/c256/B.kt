package leetcode.contest.c256

import utils.print

fun main() {
    val s = Solution5855()
    s.kthLargestNumber(arrayOf("2", "21", "12", "1"), 3).print()
}

class Solution5855 {
    fun kthLargestNumber(nums: Array<String>, k: Int): String {
        val sorted = nums.sortedWith(compareBy({ it.length }, { it }))
        sorted.joinToString().print()
        return sorted[nums.size - k]
    }
}