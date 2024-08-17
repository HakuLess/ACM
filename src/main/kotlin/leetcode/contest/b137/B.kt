package leetcode.contest.b137

import utils.print

fun main() {
    val s = SolutionB()
    s.resultsArray(intArrayOf(1, 2, 3, 4, 3, 2, 5), 3).print()
}

class SolutionB {
    fun resultsArray(nums: IntArray, k: Int): IntArray {
        val n = nums.size
        val results = IntArray(n - k + 1) { -1 }

        var maxVal = Int.MIN_VALUE
        var validCnt = 0

        for (i in 0 until k) {
            if (i > 0 && nums[i] == nums[i - 1] + 1) {
                validCnt++
            }
            maxVal = nums[i]
        }
        if (validCnt == k - 1) {
            results[0] = maxVal
        }

        for (i in k until nums.size) {
            if (nums[i - k] + 1 == nums[i - k + 1]) {
                validCnt--
//                println("-- ${i - k}: ${nums[i - k]} ${nums[i - k + 1]}")
            }
            if (nums[i] == nums[i - 1] + 1) {
                validCnt++
//                println("++ ${i}: ${nums[i - 1]} ${nums[i]}")
            }
            maxVal = nums[i]
//            println("$i: with $validCnt")
            if (validCnt == k - 1) {
                results[i - k + 1] = maxVal
            }
        }

        return results
    }
}