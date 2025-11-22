package leetcode.contest.b170

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.lexSmallestNegatedPerm(3, 0).print()
    s.lexSmallestNegatedPerm(1, 0).print()
    s.lexSmallestNegatedPerm(1, -1).print()
    s.lexSmallestNegatedPerm(2, -2).print()
}

class SolutionC {
    fun lexSmallestNegatedPerm(n: Int, target: Long): IntArray {

        val sum = n.toLong() * (n + 1) / 2
        if (target < -sum || target > sum) return intArrayOf()

        val diff = sum - target
        if (diff % 2L != 0L) return intArrayOf()

        val need = (diff / 2L)
        if (need !in 0L..sum) return intArrayOf()

        val neg = BooleanArray(n + 1)
        var rem = need
        var k = n
        while (k >= 1 && rem > 0L) {
            val kv = k.toLong()
            if (kv <= rem) {
                neg[k] = true
                rem -= kv
            }
            k--
        }
        if (rem != 0L) {
            return intArrayOf()
        }

        val ansList = ArrayList<Int>(n)
        for (i in n downTo 1) {
            if (neg[i]) ansList.add(-i)
        }
        for (i in 1..n) {
            if (!neg[i]) ansList.add(i)
        }

        val res = ansList.toIntArray()

//        if (res.map { abs(it) }.sorted().joinToString()
//            != (1..n).joinToString()
//        ) {
//            return intArrayOf()
//        }

        return res
    }
}