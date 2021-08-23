package leetcode.contest.c255

import utils.print

fun main() {
    val s = Solution5851()
    s.findDifferentBinaryString(arrayOf("01", "10")).print()
}

class Solution5851 {
    fun findDifferentBinaryString(nums: Array<String>): String {
        val n = nums[0].length
        val set = nums.map {
            var cur = 0
            var step = 1
            for (i in it.indices.reversed()) {
                cur += (it[i] - '0') * step
                step *= 2
            }
            cur
        }
        set.joinToString().print()
        for (i in 0 until (1 shl n)) {
            if (i !in set) return i.toString(2).padStart(n, '0')
        }
        return ""
    }
}