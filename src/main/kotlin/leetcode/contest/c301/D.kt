package leetcode.contest.c301

import utils.longComb
import utils.print

fun main() {
    val s = SolutionD()
    s.idealArrays(2, 5).print()
    s.idealArrays(5, 3).print()
    s.idealArrays(5878, 2900).print()
    s.idealArrays(9767, 9557).print()
}

class SolutionD {
    fun idealArrays(n: Int, maxValue: Int): Int {
        val mod = 1000000007L
        var ans = 0L
        val seen = HashMap<Int, Long>()
        fun dfs(cur: Int, cnt: Int) {
            // n - 1个数，插入cnt - 1个板子
            // 插板法获取组合可能性
            // 如 1、3、6 组成 5个 数字，和 1、3、9 组成 5个 数字，结果相同
            // 4个空位插2个板，如 11336 13336
            ans += if (cnt in seen) seen[cnt]!! else
                longComb((n - 1).toLong(), (cnt - 1).toLong()).also {
                    seen[cnt] = it
                }
            ans %= mod
            if (cnt == n) return
            for (i in 2..maxValue / cur) {
                dfs(i * cur, cnt + 1)
            }
        }
        for (i in 1..maxValue) {
            dfs(i, 1)
        }
        return ans.toInt()
    }
}