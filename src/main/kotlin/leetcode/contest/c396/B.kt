package leetcode.contest.c396

import utils.print

fun main() {
    val s = SolutionB()
    s.minimumOperationsToMakeKPeriodic("leetcoleet", 2).print()
}

class SolutionB {
    fun minimumOperationsToMakeKPeriodic(word: String, k: Int): Int {
        val map = HashMap<String, Int>()
        val n = word.length / k
        for (i in word.indices step k) {
            val sub = word.substring(i, i + k)
            map[sub] = map.getOrDefault(sub, 0) + 1
//            sub.print()
        }

        return n - map.values.maxOrNull()!!
    }
}