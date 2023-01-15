package leetcode.contest.c328

import utils.print

fun main() {
    val s = SolutionC()
    // 1
    s.countGood(intArrayOf(1, 1, 1, 1, 1), 10).print()
    // 4
    s.countGood(intArrayOf(3, 1, 4, 3, 2, 2, 4), 2).print()
    // 21
    s.countGood(intArrayOf(2, 1, 3, 1, 2, 2, 3, 3, 2, 2, 1, 1, 1, 3, 1), 11).print()
}

class SolutionC {
    fun countGood(nums: IntArray, k: Int): Long {
        var ans = 0L
        var l = 0
        val map = HashMap<Int, Long>()
        var cur = 0L
        for (r in nums.indices) {
            val it = nums[r]
            map[it] = map.getOrDefault(it, 0) + 1
            cur += map[it]!! - 1

            while (cur >= k) {
//                println("当前范围$l..$r")
                ans += (nums.size - r).toLong()
                cur -= (map[nums[l]]!! - 1)
                map[nums[l]] = map[nums[l]]!! - 1
                l++
            }
        }
        return ans
    }
}