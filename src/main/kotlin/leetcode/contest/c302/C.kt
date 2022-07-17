package leetcode.contest.c302

import java.math.BigInteger

class SolutionC {
    fun smallestTrimmedNumbers(nums: Array<String>, queries: Array<IntArray>): IntArray {
        return queries.map {
            val k = it[0]
            val trim = it[1]
            // value index
            val arr = ArrayList<Pair<BigInteger, Int>>()
            nums.forEachIndexed { index, s ->
                val v = s.takeLast(trim).toBigInteger()
                arr.add(Pair(v, index))
            }
            arr.sortWith(compareBy({ it.first }, { it.second }))
            arr[k - 1].second
        }.toIntArray()
    }
}