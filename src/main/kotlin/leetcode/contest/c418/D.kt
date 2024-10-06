package leetcode.contest.c418

import utils.gcd
import utils.print

fun main() {
    val s = SolutionD()
    s.gcdValues(intArrayOf(2, 3, 4), longArrayOf(0, 2, 2)).print()
}

class SolutionD {
    fun gcdValues(nums: IntArray, queries: LongArray): IntArray {
        val gcdList = mutableListOf<Int>()
        val n = nums.size
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                gcdList.add(gcd(nums[i], nums[j]))
            }
        }

        gcdList.sort()

        val result = IntArray(queries.size)
        for (i in queries.indices) {
            result[i] = gcdList[queries[i].toInt()]
        }

        return result
    }
}