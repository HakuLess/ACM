package leetcode.contest.b170

import utils.print

fun main() {
    val s = SolutionD()
    // 3
    s.totalWaviness(120, 130).print()
    // 3
    s.totalWaviness(198, 202).print()
    // 2
    s.totalWaviness(4848, 4848).print()
}

class SolutionD {
    fun totalWaviness(num1: Long, num2: Long): Long {

        fun count(x: Long): Long {
            if (x < 0) return 0L
            val s = x.toString()
            val n = s.length
            val digits = s.map { it - '0' }

            val seen = HashMap<String, Pair<Long, Long>>()

            fun dfs(pos: Int, last2: Int, last1: Int, started: Boolean, tight: Boolean): Pair<Long, Long> {
                if (pos == n) {
                    return Pair(1L, 0L)
                }

                val key = "$pos,$last2,$last1,$started,$tight"
                if (!tight && seen.containsKey(key)) return seen[key]!!

                val up = if (tight) digits[pos] else 9
                var totalCnt = 0L
                var totalSum = 0L

                for (d in 0..up) {
                    val nStarted = started || d != 0
                    val nTight = tight && (d == up)

                    var add = 0
                    if (nStarted && last2 != -1 && last1 != -1) {
                        if (last1 > last2 && last1 > d) add = 1
                        else if (last1 < last2 && last1 < d) add = 1
                    }

                    val newLast2 = if (nStarted) last1 else -1
                    val newLast1 = if (nStarted) d else -1

                    val (cntChild, sumChild) = dfs(pos + 1, newLast2, newLast1, nStarted, nTight)

                    totalCnt += cntChild
                    totalSum += sumChild + add * cntChild
                }

                val res = Pair(totalCnt, totalSum)
                if (!tight) seen[key] = res
                return res
            }

            return dfs(0, -1, -1, started = false, tight = true).second
        }

        return count(num2) - count(num1 - 1)
    }
}