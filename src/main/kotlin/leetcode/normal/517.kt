package leetcode.normal

import utils.print
import kotlin.math.abs

fun main() {
    val s = Solution517()
    s.findMinMoves(intArrayOf(1, 0, 5)).print()
    s.findMinMoves(intArrayOf(0, 3, 0)).print()
    s.findMinMoves(intArrayOf(0, 2, 0)).print()
}

class Solution517 {
    fun findMinMoves(machines: IntArray): Int {
        val total = machines.sum()
        val n = machines.size
        if (total % n != 0) return -1
        val avg = total / n
        var ans = 0
        var sum = 0
        for (num in machines) {
            val cur = num - avg
            sum += cur
            ans = maxOf(ans, maxOf(abs(sum), cur))
        }
        return ans
    }
}