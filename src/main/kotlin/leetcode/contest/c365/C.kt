package leetcode.contest.c365

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
    s.minSizeSubarray(intArrayOf(1, 6, 5, 5, 1, 1, 2, 5, 3, 1, 5, 3, 2, 4, 6, 6), 56).print()
}

class SolutionC {
    fun minSizeSubarray(nums: IntArray, target: Int): Int {
        val sum = nums.sum()
        val t = target % nums.sum()
        println("$sum $t")
        if (t == 0) return (target / sum) * nums.size

        val l = ArrayList<Int>()
        l.addAll(nums.toList())
        l.addAll(nums.toList())
        val preSum = l.toIntArray().preSumArray(false)
        var ans = Int.MAX_VALUE
        // value index
        val map = HashMap<Long, Int>()
        for (i in preSum.indices) {
            val key = preSum[i] - t
            if (key in map.keys) {
                ans = minOf(ans, i - map[key]!!)
            }
            map[preSum[i]] = i
        }
        return if (ans == Int.MAX_VALUE) -1 else ans + (target / sum) * nums.size
    }
}