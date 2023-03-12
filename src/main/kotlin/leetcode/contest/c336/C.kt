package leetcode.contest.c336

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionC()
    s.beautifulSubarrays(intArrayOf(4, 3, 1, 2, 4)).print()
}

class SolutionC {
    fun beautifulSubarrays(nums: IntArray): Long {
        val map = HashMap<Int, Long>()
        map[0] = 1L
        var ans = 0L
        var cur = 0
        nums.forEach {
            cur = cur xor it
            ans += map.getOrDefault(cur, 0)
            map[cur] = map.getOrDefault(cur, 0) + 1
        }
        return ans
    }
}