package leetcode.contest.b127

import utils.print

fun main() {
    val s = SolutionC()
//    s.minimumSubarrayLength(intArrayOf(1, 2, 12, 1, 16, 10), 20).print()
    s.minimumSubarrayLength(intArrayOf(5, 1, 19, 9, 2, 12, 13, 32, 3, 24, 9, 25), 61).print()
}

class SolutionC {
    fun minimumSubarrayLength(nums: IntArray, k: Int): Int {
        if (nums.any { it >= k }) return 1
        var ans = Int.MAX_VALUE

        // 从左向右
        var cur = 0
        var i = 0
        while (i in nums.indices) {
            cur = cur or nums[i]

            // 已满足，反向查找
            if (cur >= k) {
//                println("$i with $cur")
                var c = 0
                for (j in i downTo 0) {
                    c = c or nums[j]
                    if (c >= k) {
//                        println("$i down to $j")
                        ans = minOf(ans, i - j + 1)
                        i = j
                        cur = 0
                        break
                    }
                }
            }

            i++
        }
        return if (ans == Int.MAX_VALUE) -1
        else ans
    }
}