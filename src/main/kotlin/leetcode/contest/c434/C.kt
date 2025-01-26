package leetcode.contest.c434

import utils.print


fun main() {
    val s = SolutionC()
    // 2
    s.maxFrequency(intArrayOf(2, 8), 8).print()
    // 2
    s.maxFrequency(intArrayOf(1, 2, 3, 4, 5, 6), 1).print()
    // 4
    s.maxFrequency(intArrayOf(10, 2, 3, 4, 5, 5, 4, 3, 2, 2), 10).print()
    // 4
    s.maxFrequency(intArrayOf(9, 8, 9, 4, 4), 4).print()
    // 3
    s.maxFrequency(intArrayOf(2, 6, 2, 10, 3), 2).print()
}

class SolutionC {
    fun maxFrequency(nums: IntArray, k: Int): Int {
        var ans = 0
        for (num in nums) {
            if (num == k) {
                ans++
            }
        }

        val xSet = HashSet<Int>()
        for (num in nums) {
            xSet.add(k - num)
        }

        var maxOffset = 0
        for (x in xSet) {
            var currentSum = 0
            var currentMax = 0
            for (num in nums) {
                val contribution = (if (num == k - x) 1 else 0) - if (num == k) 1 else 0
                currentSum = maxOf(contribution, currentSum + contribution)
                currentMax = maxOf(currentMax, currentSum)
            }
            maxOffset = maxOf(maxOffset, currentMax)
        }

        return ans + maxOf(maxOffset, 0)
    }

}