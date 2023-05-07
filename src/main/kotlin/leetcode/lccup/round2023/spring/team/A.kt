package leetcode.lccup.round2023.spring.team

import utils.print

fun main() {
    val s = SolutionA()
    s.runeReserve(intArrayOf(1, 1, 3, 3, 2, 4)).print()
    s.runeReserve(intArrayOf(1, 3, 5, 4, 1, 7)).print()
}

class SolutionA {
    fun runeReserve(runes: IntArray): Int {
        runes.sort()
        var pre = runes[0]
        var ans = 0
        var cur = 0
        for (i in runes.indices) {
            if (runes[i] - pre <= 1) {
                cur++
                ans = maxOf(ans, cur)
            } else {
                cur = 1
            }
            pre = runes[i]
        }
        return ans
    }
}