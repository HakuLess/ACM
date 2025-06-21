package leetcode.contest.b159

import utils.SortedList
import utils.getPrimesBoolean
import utils.print

fun main() {
    val s = SolutionC()
    // 2
    s.primeSubarray(intArrayOf(1, 2, 3), 1).print()
    // 4
    s.primeSubarray(intArrayOf(2, 3, 5, 7), 3).print()
}

// Not Finished
class SolutionC {
    fun primeSubarray(nums: IntArray, k: Int): Int {

        // 预处理质数内容
        val isPrime = getPrimesBoolean(50000)

        var ans = 0

        for (i in nums.indices) {
            val sortedList = SortedList<Int>()
            for (j in i until nums.size) {
                val num = nums[j]
                if (isPrime[num]) {
                    sortedList.insert(num)
                }

                if (sortedList.size >= 2) {
                    val minPrime = sortedList.minOrNull()!!
                    val maxPrime = sortedList.maxOrNull()!!
                    if (maxPrime - minPrime <= k) {
                        ans++
                    }
                }
            }
        }

        return ans
    }
}