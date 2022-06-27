package leetcode.contest.c299

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
    s.maximumsSplicedArray(intArrayOf(60, 60, 60), intArrayOf(10, 90, 10)).print()
}

class SolutionC {
    fun maximumsSplicedArray(nums1: IntArray, nums2: IntArray): Int {
        val diff = IntArray(nums1.size)
        for (i in diff.indices) {
            diff[i] = nums1[i] - nums2[i]
        }

        // 获取子数组的最大值
        fun getMax(diff: IntArray): Int {
            var ans = Int.MIN_VALUE
            var cur = 0
            for (i in diff.indices) {
                cur += diff[i]
                cur = maxOf(cur, 0)
                ans = maxOf(ans, cur)
            }
            return ans
        }

        return maxOf(nums1.sum() + getMax(diff.map { -it }.toIntArray()), nums2.sum() + getMax(diff))
    }
}