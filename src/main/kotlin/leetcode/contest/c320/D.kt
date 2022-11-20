package leetcode.contest.c320

import utils.print

fun main() {
    val s = SolutionD()
//    s.beautifulPartitions("23542185131", 3, 2).print()
//    s.beautifulPartitions("23542185131", 3, 3).print()
//    s.beautifulPartitions("3312958", 3, 1).print()
    // 4
    s.beautifulPartitions("783938233588472343879134266968", 4, 6).print()
}

class SolutionD {
    fun beautifulPartitions(s: String, k: Int, minLength: Int): Int {
        val set = hashSetOf('2', '3', '5', '7')
        if (s[0] !in set || s.last() in set) return 0
        val mod = 1000000007L

        val seen = HashMap<Int, Long>()

        // 当前index，已分割字符串
        fun dfs(i: Int, t: Int): Long {
            val key = i * 10000 + t
            if (key in seen) return seen[key]!!

            if (t == k - 1) {
                return if (s.length - i >= minLength) 1L
                else 0L
            }
            var cur = 0L
            for (j in i + minLength - 1 until s.length) {
                // 可作为分割点
                if (s[j] !in set && j + 1 in s.indices && s[j + 1] in set) {
                    cur += dfs(j + 1, t + 1)
                    cur %= mod
                }
            }
            return cur.also {
                seen[key] = it
            }
        }

        val ans = dfs(0, 0)
        return (ans % mod).toInt()
    }
}