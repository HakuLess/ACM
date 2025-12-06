package leetcode.contest.b171

import utils.print
import java.util.TreeSet

fun main() {
    val s = SolutionB()
    s.minOperations(intArrayOf(1, 2, 4)).print()
    // 1 0 3
    s.minOperations(intArrayOf(6, 7, 12)).print()
}

class SolutionB {
    fun minOperations(nums: IntArray): IntArray {

        fun gene(limit: Int): TreeSet<Int> {
            val ts = TreeSet<Int>()

            for (len in 1..14) {
                val half = (len + 1) / 2
                val start = 1 shl (half - 1)
                val end = (1 shl half) - 1

                for (h in start..end) {
                    val s = h.toString(2)
                    val palStr = if (len % 2 == 0) {
                        s + s.reversed()
                    } else {
                        s + s.dropLast(1).reversed()
                    }
                    val v = palStr.toInt(2)
                    if (v <= limit) ts.add(v)
                }
            }
            return ts
        }

        val ts = gene(10000)
//        pal.print()
        val ans = IntArray(nums.size)

        for (i in nums.indices) {
            val x = nums[i]
            ans[i] = if (x in ts) {
                0
            } else {
                val high = ts.higher(x) ?: (Int.MAX_VALUE / 2)
                val low = ts.lower(x) ?: (Int.MIN_VALUE / 2)
                minOf(high - x, x - low)
            }
        }

        return ans
    }
}