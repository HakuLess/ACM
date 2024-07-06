package leetcode.contest.b134

import utils.print
import utils.printLong

fun main() {
    val s = SolutionD()
    // 11
//    s.countSubarrays(intArrayOf(2, 1, 2, 4, 0), 0).print()
    // 7
    s.countSubarrays(intArrayOf(9, 1, 8, 9, 5), 0).print()
}

class SolutionD {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        var ans = 0L
        var map = HashMap<Int, Long>()
        for (i in nums.indices) {
            val item = nums[i]
            val newMap = HashMap<Int, Long>()
            map.keys.forEach { key ->
                if (item and key == k) {
                    ans += map[key]!!
                }
                newMap[item and key] = newMap.getOrDefault(item and key, 0) + map[key]!!
            }
            newMap[item] = newMap.getOrDefault(item, 0) + 1
            if (item == k) {
                ans++
            }
//            newMap.printLong()
            map = newMap
        }
        return ans
    }
}