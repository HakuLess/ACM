package leetcode.lccup.b

import utils.print

fun main() {
    val s = SolutionF()
    s.ringGame(longArrayOf(5, 4, 6, 2, 7)).print()
//    s.ringGame(longArrayOf(12, 7, 11, 3, 9)).print()
//    s.ringGame(longArrayOf(1, 1, 1)).print()
//    s.ringGame(longArrayOf(1, 17, 1, 19, 1)).print()
}

class SolutionF {
    fun ringGame(challenge: LongArray): Long {

        val n = challenge.size

        fun dfs(i: Int, cur: Long): Boolean {
            var c = cur or challenge[i]
            var left = (i - 1 + n) % n
            var right = (i + 1 + n) % n
            while (left != right) {
                if (challenge[left] <= c) {
                    c = c or challenge[left]
                    left = (left - 1 + n) % n
                    continue
                }
                if (challenge[right] <= c) {
                    c = c or challenge[right]
                    right = (right + 1 + n) % n
                    continue
                }
                return false
            }
            // 只剩下最后一个没处理
            return c >= challenge[left]
        }

        fun check(cur: Long): Boolean {
            for (i in challenge.indices) {
                if (cur >= challenge[i]) {
                    if (dfs(i, cur)) return true
                }
            }
            return false
        }

        // 2^60 大于 10^18
        var cur: Long = (1L shl 60) - 1
        for (i in 59 downTo 0) {
            // 逐步按位取反，尽量减小自己
            if (check((cur and ((1L shl i).inv())))) {
                cur = cur and ((1L shl i).inv())
            }
        }
        return cur
    }
}