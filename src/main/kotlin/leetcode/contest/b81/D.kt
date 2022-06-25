package leetcode.contest.b81

import utils.gcd
import utils.print

fun main() {
    val s = SolutionD()
    s.distinctSequences(4).print()
//    s.distinctSequences(2).print()
//    s.distinctSequences(1000).print()
}

class SolutionD {
    fun distinctSequences(n: Int): Int {
        val mod = 1000000007L

        val seen = HashMap<Int, Long>()

        fun dfs(a: Int, b: Int, c: Int, index: Int): Long {
            val key = a + b * 6 + c * 36 + index * 1000
            if (key in seen) return seen[key]!!
            if (index == n) return 1L

            var ans = 0L
            if (a == 0) {
                for (i in 1..6) {
                    ans += dfs(i, b, c, index + 1)
                }
            } else if (b == 0) {
                for (i in 1..6) {
                    if (gcd(i, a) == 1 && i != a) {
                        ans += dfs(a, i, c, index + 1)
                    }
                }
            } else if (c == 0) {
                for (i in 1..6) {
                    if (gcd(i, b) == 1 && i != b && i != a) {
                        ans += dfs(a, b, i, index + 1)
                    }
                }
            } else {
                for (i in 1..6) {
                    if (gcd(i, c) == 1 && i != b && i != c) {
                        ans += dfs(b, c, i, index + 1)
                    }
                }
            }
            return (ans % mod).also {
                seen[key] = it
            }
        }

        return (dfs(0, 0, 0, 0) % mod).toInt()
    }
}