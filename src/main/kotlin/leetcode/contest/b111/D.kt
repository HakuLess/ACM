package leetcode.contest.b111

import utils.print

fun main() {
    val s = SolutionD()
    s.numberOfBeautifulIntegers(10, 20, 3).print()
    s.numberOfBeautifulIntegers(1, 10, 1).print()
    s.numberOfBeautifulIntegers(5, 5, 2).print()
}

class SolutionD {
    fun numberOfBeautifulIntegers(low: Int, high: Int, k: Int): Int {
        fun countDigits(n: Int): IntArray {
            val counts = IntArray(10)
            var num = n
            while (num > 0) {
                val digit = num % 10
                counts[digit]++
                num /= 10
            }
            return counts
        }

        val dp = Array(20) { IntArray(k) { 0 } }
        dp[1][0] = 1

        for (i in 2 until 20) {
            for (j in 0 until k) {
                for (d in 0..9) {
                    if (i % 2 == 1 || d % 2 == 1) {
                        dp[i][(j + d) % k] += dp[i - 1][j]
                    }
                }
            }
        }

        val lowCounts = countDigits(low - 1)
        val highCounts = countDigits(high)

        fun getSum(arr: IntArray): Int {
            var sum = 0
            for (i in arr) {
                sum += i
            }
            return sum
        }

        val lowSum = getSum(dp[lowCounts.size])
        val highSum = getSum(dp[highCounts.size])

        var count = highSum - lowSum
        if (lowCounts.sum() % 2 == 0 && low % k == 0) {
            count++
        }

        return count
    }
}