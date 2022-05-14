package leetcode.contest.b78

import utils.print

fun main() {
    val s = SolutionA()
    s.divisorSubstrings(430043, 2).print()
}

class SolutionA {
    fun divisorSubstrings(num: Int, k: Int): Int {
        var ans = 0
        val s = num.toString()
        for (i in 0..s.length - k) {
            val sub = s.substring(i, i + k).toInt()
            if (sub == 0) continue
            if (num % sub == 0) {
                ans++
            }
        }
        return ans
    }
}