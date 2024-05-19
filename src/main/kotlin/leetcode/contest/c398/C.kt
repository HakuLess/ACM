package leetcode.contest.c398

import utils.print
import utils.printInt

fun main() {
    val s = SolutionC()
    s.sumDigitDifferences(intArrayOf(13, 23, 12)).print()
}

class SolutionC {
    fun sumDigitDifferences(nums: IntArray): Long {
        val n = nums[0].toString().length
        var mod = 10
        var ans = 0L
        val total = nums.size
        repeat(n) {
            val map = HashMap<Int, Int>()
            for (i in nums.indices) {
                val c = nums[i] % mod / (mod / 10)
                map[c] = map.getOrDefault(c, 0) + 1
            }

            map.printInt()

            map.forEach { k, v ->
                ans += 1L * v * (total - v)
            }
            mod *= 10
        }
        return ans / 2
    }
}