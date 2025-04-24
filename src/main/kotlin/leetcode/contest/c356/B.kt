package leetcode.contest.c356

import utils.print

fun main() {
    val s = SolutionB()
    // 4
    s.countCompleteSubarrays(intArrayOf(1, 3, 1, 2, 2)).print()
    // 10
    s.countCompleteSubarrays(intArrayOf(5, 5, 5, 5)).print()
}

class SolutionB {
    fun countCompleteSubarrays(nums: IntArray): Int {
        val totalSize = nums.toHashSet().size
        val n = nums.size
        var ans = 0
        var right = 0
        val map = HashMap<Int, Int>()
        for (left in nums.indices) {
            while (right in nums.indices && map.keys.size != totalSize) {
                map[nums[right]] = map.getOrDefault(nums[right], 0) + 1
                right++
            }

            if (map.keys.size != totalSize) {
                return ans
            }

            ans += n - right + 1

            map[nums[left]] = map.getOrDefault(nums[left], 0) - 1
            if (map[nums[left]] == 0) {
                map.remove(nums[left])
            }
        }
        return ans
    }
}