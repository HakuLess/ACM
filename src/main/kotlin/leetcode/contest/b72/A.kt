package leetcode.contest.b72

import utils.print

fun main() {
    val s = SolutionA()
    s.countPairs(intArrayOf(3, 1, 2, 2, 2, 1, 3), 2).print()
}

class SolutionA {
    fun countPairs(nums: IntArray, k: Int): Int {
        var ans = 0
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] == nums[j] && ((i * j) % k == 0)) {
                    ans++
                }
            }
        }
        return ans
    }
}