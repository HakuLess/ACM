package leetcode.other.ubiquant2022

import utils.print

fun main() {
    val s = SolutionA()
    s.numberOfPairs(intArrayOf(17, 28, 39, 71)).print()
    s.numberOfPairs(intArrayOf(71, 60)).print()
}

class SolutionA {
    fun numberOfPairs(nums: IntArray): Int {
        val mod = 1000000007L
        val map = HashMap<Long, Long>()
        for (i in nums.indices) {
            val c = nums[i].toLong() - nums[i].toString().reversed().toLong()
            map[c] = map.getOrDefault(c, 0) + 1
        }
        var ans = 0L
        for (i in map.keys) {
            ans += map[i]!! * (map[i]!! - 1) / 2
            ans %= mod
        }
//        println(map)
        return ans.toInt()
    }
}