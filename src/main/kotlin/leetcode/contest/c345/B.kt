package leetcode.contest.c345

import utils.print

fun main() {
    val s = SolutionB()
    s.doesValidArrayExist(intArrayOf(1, 1, 0)).print()
}

class SolutionB {
    fun doesValidArrayExist(derived: IntArray): Boolean {
        val cur = IntArray(derived.size)
        for (i in 1 until derived.size) {
            cur[i] = cur[i - 1] xor derived[i - 1]
        }
        if (derived.last() == cur[0] xor cur.last()) return true

        cur[0] = 1
        for (i in 1 until derived.size) {
            cur[i] = cur[i - 1] xor derived[i - 1]
        }
        if (derived.last() == cur[0] xor cur.last()) return true

        return false
    }
}