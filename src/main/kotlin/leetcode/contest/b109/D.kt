package leetcode.contest.b109

import utils.print

fun main() {
    val s = SolutionD()
    s.numberOfWays(10, 2).print()
    s.numberOfWays(4, 1).print()
    s.numberOfWays(160, 3).print()
    s.numberOfWays(1, 1).print()
}

class SolutionD {
    fun numberOfWays(n: Int, x: Int): Int {
        val mod = 1000000007L

        val l = ArrayList<Int>()
        var c = 1
        var item = 1
        while (item <= n) {
            item = 1
            repeat(x) {
                item *= c
            }
            l.add(item)
            c++
        }

        val seen = HashMap<String, Long>()
        fun dfs(cur: Int, index: Int): Long {
            if (cur == 0) return 1L
            if (index !in l.indices) return 0L
            if (cur < 0) return 0L
            val key = "$cur $index"
            if (key in seen) return seen[key]!!

            var tmp = 0L
            tmp += dfs(cur, index + 1)
            tmp += dfs(cur - l[index], index + 1)

            return tmp.also {
                seen[key] = it
            }
        }

        return (dfs(n, 0) % mod).toInt()
    }
}