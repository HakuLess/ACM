package leetcode.contest.c345

import utils.print

fun main() {
    val s = SolutionA()
    s.circularGameLosers(4, 4).print()
}

class SolutionA {
    fun circularGameLosers(n: Int, k: Int): IntArray {
        var cur = 1
        val set = HashSet<Int>()
        var step = 1
        while (cur !in set) {
            set.add(cur)
            cur += k * step
            cur %= n
            if (cur == 0) cur = n
            step++
        }
        return (1..n).filter { it !in set }.toIntArray()
    }
}