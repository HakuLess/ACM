package leetcode.contest.c314

import utils.print

fun main() {
    val s = SolutionB()
    s.findArray(intArrayOf(5, 2, 0, 3, 1)).print()
}

class SolutionB {
    fun findArray(pref: IntArray): IntArray {
        val ans = IntArray(pref.size)
        ans[0] = pref[0]
        for (i in 1 until pref.size) {
            ans[i] = pref[i - 1] xor pref[i]
        }
        return ans
    }
}