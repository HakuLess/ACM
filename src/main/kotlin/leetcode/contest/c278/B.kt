package leetcode.contest.c278

import utils.print

fun main() {
    val s = SolutionB()
    s.maxScoreIndices(intArrayOf(0, 0, 1, 0)).joinToString().print()
//    [0,0,1,0]
//    [0,0,0]
//    [1,1]
}

class SolutionB {
    fun maxScoreIndices(nums: IntArray): List<Int> {
        val left = IntArray(nums.size + 1)
        val right = IntArray(nums.size + 1)
        for (i in 1..nums.size) {
            left[i] = left[i - 1]
            if (nums[i - 1] == 0) {
                left[i]++
            }
        }
        for (i in nums.indices.reversed()) {
            right[i] = right[i + 1]
            if (nums[i] == 1) {
                right[i]++
            }
        }
        left.print()
        right.print()
        val ans = ArrayList<Int>()
        var max = 0
        for (i in left.indices) {
            if (left[i] + right[i] > max) {
                max = left[i] + right[i]
                ans.clear()
                ans.add(i)
            } else if (left[i] + right[i] == max) {
                ans.add(i)
            }
        }
        return ans
    }
}