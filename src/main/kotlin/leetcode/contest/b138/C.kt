package leetcode.contest.b138

import utils.permute
import utils.permuteUnique
import utils.print

fun main() {
    val s = SolutionC()
    s.countGoodIntegers(5, 6).print()
    for (n in 1..9) {
        for (k in 1..9) {
            println("map[Pair($n, $k)] = ${s.countGoodIntegers(n, k)}")
        }
    }
}

class SolutionC {
    fun countGoodIntegers(n: Int, k: Int): Long {
        return 0L
    }
}