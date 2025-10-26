package leetcode.contest.c473

import utils.preSumArray
import utils.print

fun main() {
    val s = SolutionC()
    s.countStableSubarrays(intArrayOf(-4, 4, 0, 0, -8, -4)).print()
}

class SolutionC {
    fun countStableSubarrays(capacity: IntArray): Long {
        val n = capacity.size
        val prefix = capacity.preSumArray()

        val map = HashMap<Long, HashMap<Long, Long>>()
        var ans = 0L

        for (r in 2 until n) {
            val l = r - 2
            val capL = capacity[l].toLong()

            // left 后添加，防止仅2个元素的参与计算
            val leftPrefix = prefix[l + 1]
            val inner = map.computeIfAbsent(capL) { HashMap() }
            inner[leftPrefix] = inner.getOrDefault(leftPrefix, 0L) + 1L

            val capR = capacity[r].toLong()
            val targetPrefix = prefix[r] - capR
            map[capR]?.get(targetPrefix)?.let { ans += it }
        }

        return ans
    }
}