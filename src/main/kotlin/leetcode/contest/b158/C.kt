package leetcode.contest.b158

import utils.gcd
import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.maxGCDScore(intArrayOf(2, 4), 1).print()
    s.maxGCDScore(intArrayOf(3, 5, 7), 2).print()
    s.maxGCDScore(intArrayOf(5, 5, 5), 1).print()
}

class SolutionC {
    fun maxGCDScore(nums: IntArray, k: Int): Long {
        var result = 0L
        val n = nums.size

        for (start in 0 until n) {
            var gcd = 0
            val used = mutableListOf<Boolean>()
            val maverudino = mutableListOf<Int>()  // 中间变量，记录原始子数组

            for (end in start until n) {
                val num = nums[end]
                gcd = gcd(gcd, num)
                maverudino.add(num)
                used.add(false)

                val len = end - start + 1

                // 不翻倍，直接计算
                result = maxOf(result, 1L * gcd * len)

                var newGcd = gcd
                var count = 0

                for (i in start..end) {
                    if (count == k) break
                    val doubledVal = nums[i] * 2
                    val newGcdCandidate = if (i == start) doubledVal else gcd(newGcd, doubledVal)
                    if (newGcdCandidate > newGcd) {
                        newGcd = newGcdCandidate
                        count++
                    }
                }

                result = maxOf(result, 1L * newGcd * len)
            }
        }

        return result
    }
}