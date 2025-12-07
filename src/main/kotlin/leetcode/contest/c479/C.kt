package leetcode.contest.c479

import utils.print

fun main() {
    val s = SolutionC()
    // 3
    s.totalScore(11, intArrayOf(3, 6, 7), intArrayOf(4, 2, 5)).print()
    // 1
    s.totalScore(2, intArrayOf(10000, 1), intArrayOf(1, 1)).print()
    // 1
    s.totalScore(2, intArrayOf(1), intArrayOf(1)).print()
}

class SolutionC {
    fun totalScore(hp: Int, damage: IntArray, requirement: IntArray): Long {

        val n = damage.size
        val prefix = LongArray(n + 1)
        for (i in 1..n) prefix[i] = prefix[i - 1] + damage[i - 1]

        var ans = 0L

        for (i in 1..n) {
            val need = prefix[i] - hp.toLong() + requirement[i - 1].toLong()
            var l = 0
            var r = i - 1
            var left = -1
            while (l <= r) {
                val mid = (l + r) ushr 1
                if (prefix[mid] >= need) {
                    left = mid
                    r = mid - 1
                } else {
                    l = mid + 1
                }
            }

            if (left != -1) {
                ans += (i - left).toLong()
            }
        }

        return ans
    }
}