package leetcode.contest.c421

import utils.gcd
import utils.print
import utils.printLong

fun main() {
    val s = SolutionC()
    s.subsequencePairCount(intArrayOf(1, 2, 3, 4)).print()
}

class SolutionC {
    fun subsequencePairCount(nums: IntArray): Int {
        val mod = 1_000_000_007L
        val mapLeft = HashMap<Int, Long>()

        var ans = 0L

        for (i in nums.indices) {
            val c = nums[i]

            // 历史所有元素merge
            mapLeft.keys.forEach { key ->
                val new = gcd(key, c)
                val cnt = mapLeft[key]!!

                mapLeft[new] = mapLeft.getOrDefault(new, 0) + cnt
            }

            // 仅当前元素自己
            mapLeft[c] = mapLeft.getOrDefault(c, 0) + 1
        }

        mapLeft.printLong()


        return ans.toInt()
    }
}