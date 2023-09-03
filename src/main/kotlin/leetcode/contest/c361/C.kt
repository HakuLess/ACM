package leetcode.contest.c361

import utils.print
import java.util.Map


fun main() {
    val s = SolutionC()
    s.countInterestingSubarrays(listOf(3, 2, 4), 2, 1).print()
    s.countInterestingSubarrays(listOf(3, 1, 9, 6), 3, 0).print()


    s.countInterestingSubarrays(listOf(11, 12, 21, 31), 10, 1).print()
    s.countInterestingSubarrays(listOf(4, 5), 1, 0).print()

    // 3
    s.countInterestingSubarrays(listOf(2, 2, 1, 2), 3, 2).print()
}

class SolutionC {
    fun countInterestingSubarrays(nums: List<Int>, modulo: Int, k: Int): Long {
        var ans = 0L
        var c = 0
        val map = HashMap<Int, Long>()
        map[0] = 1L
        for (i in nums.indices) {
            if (nums[i] % modulo == k) {
                c++
                c %= modulo
            }
            ans += map.getOrDefault((c - k + modulo) % modulo, 0L)
            map[c] = map.getOrDefault(c, 0) + 1
        }
        return ans
    }
}