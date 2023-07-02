package leetcode.contest.c352

import utils.getPrimes
import utils.print

fun main() {
    val s = SolutionB()
    s.findPrimePairs(241).print()
}

class SolutionB {
    fun findPrimePairs(n: Int): List<List<Int>> {
        val l = getPrimes(n)
        val set = l.toHashSet()
        val ans = ArrayList<ArrayList<Int>>()
        for (i in l.indices) {
            val a = l[i]
            val b = n - l[i]
            if (b >= a && b in set) {
                ans.add(arrayListOf(a, b))
            }
            if (a > n / 2) break
        }
        return ans
    }
}