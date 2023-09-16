package leetcode.contest.b113

import kotlin.collections.HashMap

class SolutionB {
    fun minLengthAfterRemovals(nums: List<Int>): Int {
        val map = HashMap<Int, Int>()
        var maxSize = 0
        val n = nums.size
        nums.forEach {
            map[it] = map.getOrDefault(it, 0) + 1
            maxSize = maxOf(maxSize, map[it]!!)
        }
        return if (maxSize <= n / 2)  n % 2
        else return n - (n - maxSize) * 2
    }
}