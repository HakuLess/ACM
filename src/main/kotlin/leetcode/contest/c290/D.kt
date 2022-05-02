package leetcode.contest.c290

import utils.*

fun main() {
    val s = SolutionD()
    s.fullBloomFlowers("[[1,6],[3,7],[9,12],[4,13]]".toGrid(), intArrayOf(2, 3, 7, 11)).print()
    s.fullBloomFlowers("[[1,10],[3,3]]".toGrid(), intArrayOf(3, 3, 2)).print()
}

class SolutionD {
    fun fullBloomFlowers(flowers: Array<IntArray>, persons: IntArray): IntArray {
        val ans = IntArray(persons.size)
        val bit = BitTree(Int.MAX_VALUE / 4)
        flowers.forEach {
            bit.add(it[0], it[1], 1)
        }
        for (i in persons.indices) {
            ans[i] = bit.query(persons[i], persons[i])
        }
        return ans
    }
}