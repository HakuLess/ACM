package leetcode.contest.c487

import utils.print

fun main() {
    val s = SolutionD()
    // 4
    s.longestAlternating(intArrayOf(2, 1, 3, 2)).print()
    // 4
    s.longestAlternating(intArrayOf(3, 2, 1, 2, 3, 2, 1)).print()
    // 1
    s.longestAlternating(intArrayOf(100000, 100000)).print()
    // 2
    s.longestAlternating(intArrayOf(1, 2, 2, 3)).print()
    // 8
    s.longestAlternating(
        intArrayOf(
            92228,
            8256,
            25044,
            74385,
            29060,
            31276,
            18678,
            71171,
            58717,
            11956,
            10545,
            41951,
            66577,
            64132,
            14295
        )
    ).print()
}

class SolutionD {
    fun longestAlternating(nums: IntArray): Int {
        val n = nums.size
        if (n <= 1) return n

        // up[i]   : 以 i 结尾，且最后是上升（nums[i-1] < nums[i]）的最长交替子数组长度
        // down[i] : 以 i 结尾，且最后是下降（nums[i-1] > nums[i]）的最长交替子数组长度
        val up = IntArray(n) { 1 }
        val down = IntArray(n) { 1 }
        for (i in 1 until n) {
            when {
                nums[i] > nums[i - 1] -> {
                    up[i] = down[i - 1] + 1
                    down[i] = 1
                }
                nums[i] < nums[i - 1] -> {
                    down[i] = up[i - 1] + 1
                    up[i] = 1
                }
                else -> {
                    up[i] = 1
                    down[i] = 1
                }
            }
        }

        // upRight[i]   : 以 i 开头，且第一个比较是上升（nums[i] < nums[i+1]）的最长交替子数组长度
        // downRight[i] : 以 i 开头，且第一个比较是下降（nums[i] > nums[i+1]）的最长交替子数组长度
        val upRight = IntArray(n) { 1 }
        val downRight = IntArray(n) { 1 }
        for (i in n - 2 downTo 0) {
            when {
                nums[i] < nums[i + 1] -> {
                    upRight[i] = downRight[i + 1] + 1
                    downRight[i] = 1
                }
                nums[i] > nums[i + 1] -> {
                    downRight[i] = upRight[i + 1] + 1
                    upRight[i] = 1
                }
                else -> {
                    upRight[i] = 1
                    downRight[i] = 1
                }
            }
        }

        // 不删除任何元素的情况
        var ans = 1
        for (i in 0 until n) {
            ans = maxOf(ans, up[i], down[i])
        }

        // 尝试删除中间的一个元素，即下标为 k (1 <= k <= n-2)
        for (k in 1 until n - 1) {
            if (nums[k - 1] < nums[k + 1]) {
                // 删除 k 后，连接处为上升，需要左边最后是下降，右边第一个是下降
                ans = maxOf(ans, down[k - 1] + downRight[k + 1])
            } else if (nums[k - 1] > nums[k + 1]) {
                // 删除 k 后，连接处为下降，需要左边最后是上升，右边第一个是上升
                ans = maxOf(ans, up[k - 1] + upRight[k + 1])
            }
            // 若 nums[k-1] == nums[k+1]，则无法形成交替连接，跳过
        }

        return ans
    }
}