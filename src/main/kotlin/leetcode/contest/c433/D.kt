package leetcode.contest.c433

import utils.originC
import java.util.*

class Solution {
    fun minMaxSubarraySum(nums: IntArray, k: Int): Long {
        var ans = 0L
        val n = nums.size

        // 单调队列来保存窗口中的最小值和最大值
        val maxDeque = LinkedList<Int>()  // 递减队列
        val minDeque = LinkedList<Int>()  // 递增队列

        for (start in 0 until n) {
            maxDeque.clear()
            minDeque.clear()

            var currentMax = Int.MIN_VALUE
            var currentMin = Int.MAX_VALUE

            for (end in start until minOf(n, start + k)) {
                // 维护最大值队列
                while (maxDeque.isNotEmpty() && nums[maxDeque.last()] <= nums[end]) {
                    maxDeque.removeLast()
                }
                maxDeque.add(end)

                // 维护最小值队列
                while (minDeque.isNotEmpty() && nums[minDeque.last()] >= nums[end]) {
                    minDeque.removeLast()
                }
                minDeque.add(end)

                // 获取当前子数组的最大值和最小值
                currentMax = nums[maxDeque.first()]
                currentMin = nums[minDeque.first()]

                // 计算当前子数组的最大值和最小值之和
                ans += (currentMax + currentMin)
            }
        }

        return ans
    }
}