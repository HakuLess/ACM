package leetcode.contest.c453

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    // 6
    s.countPartitions(intArrayOf(9, 4, 1, 3, 7), 4).print()
}

class SolutionC {
    fun countPartitions(nums: IntArray, k: Int): Int {
        val mod = 1_000_000_007L

        val n = nums.size
        val dp = LongArray(n + 1)
        dp[0] = 1L

        // 单调栈
        val maxDeque: Deque<Int> = LinkedList<Int>()
        val minDeque: Deque<Int> = LinkedList<Int>()

        var left = 0

        val prefixDp = LongArray(n + 1)
        prefixDp[0] = 1

        for (right in 1..n) {
            val num = nums[right - 1]
            while (maxDeque.isNotEmpty() && nums[maxDeque.last] <= num) {
                maxDeque.removeLast()
            }
            maxDeque.addLast(right - 1)

            while (minDeque.isNotEmpty() && nums[minDeque.last] >= num) {
                minDeque.removeLast()
            }
            minDeque.addLast(right - 1)

            while (nums[maxDeque.first] - nums[minDeque.first] > k) {
                if (maxDeque.first == left) maxDeque.removeFirst()
                if (minDeque.first == left) minDeque.removeFirst()
                left++
            }

            dp[right] = ((prefixDp[right - 1] - (if (left > 0) prefixDp[left - 1] else 0)) % mod + mod) % mod
            prefixDp[right] = (prefixDp[right - 1] + dp[right]) % mod
        }

        return dp[n].toInt()
    }
}