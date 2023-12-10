package leetcode.contest.c375

import utils.toBigInteger
import kotlin.math.pow

class SolutionB {
    fun getGoodIndices(variables: Array<IntArray>, target: Int): List<Int> {
        val ans = ArrayList<Int>()
        for (i in variables.indices) {
            val (a, b, c, m) = variables[i]
            if (((a.toBigInteger().pow(b).mod(10L.toBigInteger())).pow(c).mod(m.toBigInteger())).toInt() == target) {
                ans.add(i)
            }
        }
        return ans
    }
}