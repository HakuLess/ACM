package leetcode.contest.b93

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.maxJump(intArrayOf(0, 2, 5, 6, 7)).print()
}

class SolutionC {
    fun maxJump(stones: IntArray): Int {
        val n: Int = stones.size
        var ans = stones[1] - stones[0]
        for (i in 0 until n - 2) {
            ans = maxOf(ans, stones[i + 2] - stones[i])
        }
        return ans
    }
}