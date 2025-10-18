package leetcode.contest.c423

import utils.print

fun main() {
    val s = SolutionB()
    s.maxIncreasingSubarrays(listOf(2, 5, 7, 8, 9, 2, 3, 4, 3, 1)).print()

}

class SolutionB {
    fun maxIncreasingSubarrays(nums: List<Int>): Int {

        val l = ArrayList<Int>()
        var cur = 0
        for (i in nums.indices) {
            if (i == 0) {
                cur++
                continue
            }
            if (nums[i] > nums[i - 1]) {
                cur++
            } else {
                l.add(cur)
                cur = 1
            }
        }
        l.add(cur)
//        l.joinToString().print()

        var ans = 0
        for (i in l.indices) {
            ans = maxOf(ans, l[i] / 2)
            if (i > 0) {
                ans = maxOf(ans, minOf(l[i], l[i - 1]))
            }
        }
        return ans
    }
}