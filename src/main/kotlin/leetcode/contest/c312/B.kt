package leetcode.contest.c312

import utils.print

fun main() {
    val s = SolutionB()
    s.longestSubarray(intArrayOf(1, 2, 3, 3, 2, 2)).print()
}

class SolutionB {
    fun longestSubarray(nums: IntArray): Int {
        val max = nums.maxOrNull()!!
//        val max = nums.max()!!
        var ans = 1
        val seen = HashSet<Int>()
        for (i in nums.indices) {
            if (i in seen) continue
            if (nums[i] == max) {
                var l = i
                var r = i
                while (l in nums.indices && (max and nums[l] == max)) {
                    seen.add(l)
                    l--
                }
                while (r in nums.indices && (max and nums[r] == max)) {
                    seen.add(r)
                    r++
                }
                println("$l to $r")
                ans = maxOf(ans, r - l - 1)
            }
        }
        return ans
    }
}