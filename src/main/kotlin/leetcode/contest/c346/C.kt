package leetcode.contest.c346

import utils.print

fun main() {
    val s = SolutionC()
    s.punishmentNumber(37).print()
}

class SolutionC {
    fun punishmentNumber(n: Int): Int {
        val set = hashSetOf(
            1,
            9,
            10,
            36,
            45,
            55,
            82,
            91,
            99,
            100,
            235,
            297,
            369,
            370,
            379,
            414,
            657,
            675,
            703,
            756,
            792,
            909,
            918,
            945,
            964,
            990,
            991,
            999,
            1000
        )
        var ans = 0
        for (i in 1..n) {
            val c = i * i
            if (i in set) {
                ans += c
            }
        }
        return ans
    }

    private fun check(c: Int, target: Int): Boolean {
        val l = ArrayList<Int>()
        var cur = c
        while (cur > 0) {
            l.add(cur % 10)
            cur /= 10
        }
        l.reverse()
        fun dfs(sum: Int, index: Int): Boolean {
            println("sum $sum with $index")
            if (index !in l.indices) {
                return sum == target
            }
            var ans = false
            for (i in index until l.size) {
                l.subList(index, i + 1).joinToString("").print()
                ans = ans or dfs(sum + l.subList(index, i + 1).joinToString("").toInt(), i + 1)
            }
            return ans
        }
        return dfs(0, 0)
    }
}