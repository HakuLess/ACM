package leetcode.contest.c432

import utils.print
import java.util.*


fun main() {
    val s = SolutionD()
    s.countNonDecreasingSubarrays(intArrayOf(6, 3, 1, 2, 4, 4), 7).print()
    s.countNonDecreasingSubarrays(intArrayOf(6, 3, 1, 3, 6), 4).print()
}

// 单调栈
// https://zhwebsite.com/2025/01/12/leetcode-contest-432/
class SolutionD {
    fun countNonDecreasingSubarrays(nums: IntArray, k: Int): Long {
        var lastK = k.toLong()
        val prefixSum = LongArray(nums.size + 1)
        for (i in nums.indices) {
            prefixSum[i + 1] = prefixSum[i] + nums[i]
        }

        var res = 0L
        val deque = ArrayDeque<Int>() // 单调栈
        var st = nums.size - 1
        deque.addLast(st)

        for (ed in nums.size - 1 downTo 0) {
            while (lastK >= 0 && st >= 0) {
                st--
                if (st == -1) break

                // 更新单调栈范围，模拟最大可行范围
                while (deque.isNotEmpty() && nums[deque.first()] <= nums[st]) {
                    val tmp = deque.removeFirst()
                    lastK -= if (deque.isEmpty()) {
                        (ed + 1 - tmp) * (nums[st] - nums[tmp]).toLong()
                    } else {
                        (deque.first() - tmp) * (nums[st] - nums[tmp]).toLong()
                    }
                }
                deque.addFirst(st)
            }

            // 统计以 `ed` 为右端点的所有非递减子数组数量
            res += (ed - st)

            // 调整 k 和单调栈
            lastK += nums[deque.last()] - nums[ed]
            if (deque.last() == ed) {
                deque.removeLast()
            }
        }

        return res
    }
}