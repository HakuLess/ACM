package leetcode.contest.c454

import utils.print

fun main() {
    val s = SolutionB()
    s.specialTriplets(intArrayOf(8, 4, 2, 8, 4)).print()
}

class SolutionB {
    fun specialTriplets(nums: IntArray): Int {
        val mod = 1_000_000_007L
        val n = nums.size
        // 左侧是当前值两倍的
        val leftC = LongArray(n)
        // 右侧是当前值两倍的
        val rightC = LongArray(n)
        val map = HashMap<Int, Long>()
        for (i in nums.indices) {
            val left = map.getOrDefault(nums[i] * 2, 0L)
            leftC[i] = left
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
        }

        map.clear()

        for (i in nums.indices.reversed()) {
            val right = map.getOrDefault(nums[i] * 2, 0L)
            rightC[i] = right
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
        }

        var ans = 0L
        for (i in nums.indices) {
            val tmp = leftC[i] * rightC[i]
            ans += tmp
            ans %= mod
        }
        return ans.toInt()
    }
}