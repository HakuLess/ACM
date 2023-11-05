package leetcode.contest.c370

import utils.print
import java.util.*

fun main() {
    val s = SolutionD()
//    s.maxBalancedSubsequenceSum(intArrayOf(3, 3, 5, 6)).print()
//    s.maxBalancedSubsequenceSum(intArrayOf(2, 7)).print()
    // 13
    s.maxBalancedSubsequenceSum(intArrayOf(-1, 4, 8, 5, 8, 2, -8)).print()
}

class SolutionD {
    fun maxBalancedSubsequenceSum(nums: IntArray): Long {
        val tm = TreeMap<Int, Long>()
        for (i in nums.indices) {
            val key = nums[i] - i

            if (key in tm.keys) {
                tm[key] = maxOf(nums[i].toLong(), tm[key]!! + nums[i])
            } else {
                val floor = tm.floorKey(key)
                if (floor != null) {
                    tm[key] = maxOf(nums[i].toLong(), tm[floor]!! + nums[i])
                } else {
                    tm[key] = nums[i].toLong()
                }
            }

            var remove = true
            while (remove) {
                val next = tm.ceilingKey(key + 1)
                if (next != null && tm[key]!! >= tm[next]!!) {
                    tm.remove(next)
                } else {
                    remove = false
                }
            }
        }
        return tm.values.maxOrNull()!!
    }
}