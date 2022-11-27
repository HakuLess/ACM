package leetcode.contest.c321

import utils.print
import utils.printInt

fun main() {
    val s = SolutionD()
    // 3 [4] [4,5] [1,4,5]
    s.countSubarrays(intArrayOf(3, 2, 1, 4, 5), 4).print()
//    s.countSubarrays(intArrayOf(2, 3, 1), 3).print()

    // 3 [1 4] [1] [5 1]
    s.countSubarrays(intArrayOf(2, 5, 1, 4, 3, 6), 1).print()

    // 13
    s.countSubarrays(intArrayOf(5, 19, 11, 15, 13, 16, 4, 6, 2, 7, 10, 8, 18, 20, 1, 3, 17, 9, 12, 14), 6).print()
}

class SolutionD {
    fun countSubarrays(nums: IntArray, k: Int): Int {
        val n = nums.size
        val t = nums.indexOf(k)
        var l = 0
        val left = HashMap<Int, Int>()
        for (i in t downTo 0) {
            if (nums[i] > k) l++
            else if (nums[i] < k) l--
            left[l] = left.getOrDefault(l, 0) + 1
        }

        var r = 0
        val right = HashMap<Int, Int>()
        for (i in t until n) {
            if (nums[i] > k) r++
            else if (nums[i] < k) r--
            right[r] = right.getOrDefault(r, 0) + 1
        }

        var ans = 0
        for (x in left.keys) {
            ans += left[x]!! * (right.getOrDefault(-x, 0) + right.getOrDefault(1 - x, 0))
        }
        return ans
    }
}