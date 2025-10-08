package leetcode.contest.b80

import utils.biMax
import utils.biMin
import utils.print

fun main() {
    val s = SolutionB()
//    s.successfulPairs(
//        intArrayOf(5, 1, 3),
//        intArrayOf(1, 2, 3, 4, 5),
//        7
//    ).print()

    s.successfulPairs(
        intArrayOf(9),
        intArrayOf(35, 40, 22, 37, 29, 22),
        320
    ).print()

}

class SolutionB {
    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {
        potions.sort()
//        potions.print()
        val ans = IntArray(spells.size)
        for (i in spells.indices) {
            ans[i] = potions.size - biMin(l = 0L, r = potions.lastIndex.toLong()) {
                1L * spells[i] * potions[it.toInt()] >= success
            }.toInt().let { if (it == -1) potions.size else it }
        }
        return ans
    }
}