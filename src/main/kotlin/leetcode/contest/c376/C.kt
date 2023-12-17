package leetcode.contest.c376

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionC()
//    s.minimumCost(intArrayOf(10, 12, 13, 14, 15)).print()
//    s.minimumCost(intArrayOf(1, 2, 3, 4, 5)).print()
//    s.minimumCost(intArrayOf(4, 3, 1)).print()
//    s.minimumCost(intArrayOf(101, 102, 105, 108, 124)).print()

//    s.minimumCost(intArrayOf(308, 313, 319, 322)).print()
    s.minimumCost(intArrayOf(101, 115, 116, 120, 122)).print()
}

class SolutionC {
    fun minimumCost(nums: IntArray): Long {
        fun nearestPalindrome(num: Int): ArrayList<Int> {
            fun isPalindrome(x: Int): Boolean {
                return x.toString() == x.toString().reversed()
            }

            var smaller = num
            var larger = num
            while (!isPalindrome(smaller)) {
                smaller--
            }
            while (!isPalindrome(larger)) {
                larger++
            }
            return arrayListOf(smaller, larger)
        }

        nums.sort()
        val n = nums.size
//        val avg = nums.average().toInt()
        val arr = arrayListOf<Int>()
        arr.addAll(nearestPalindrome(nums[n / 2]))
        if (n / 2 - 1 in nums.indices)
            arr.addAll(nearestPalindrome(nums[n / 2 - 1]))

        var ans = Array<Long>(arr.size) { 0L }
        for (i in nums.indices) {
            for (j in ans.indices) {
                ans[j] = ans[j] + abs(nums[i] - arr[j])
            }
        }
        return ans.minOrNull()!!
    }
}