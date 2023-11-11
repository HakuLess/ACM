package leetcode.contest.b117

import utils.print

fun main() {
    val s = SolutionB()
    s.distributeCandies(3, 3).print()
}

class SolutionB {
    fun distributeCandies(n: Int, limit: Int): Long {
        var ans = 0L
        for (i in 0..minOf(n, limit)) {
            // 第一个分到i，剩余2人分n - i
            val left = n - i
            // 过多 无法分
            if (left > limit * 2) continue

            ans += minOf(left + 1, limit * 2 - left + 1)
            println("a: $i with $left dis ${left + 1} ${limit * 2 - left + 1}")
        }
        return ans
    }
}