package leetcode.normal

import utils.print
import kotlin.math.abs

fun main() {
    val s = Solution1005()
    s.largestSumAfterKNegations(intArrayOf(-2, 9, 9, 8, 4), 5).print()
}

class Solution1005 {
    fun largestSumAfterKNegations(nums: IntArray, k: Int): Int {
        var pos: List<Int>
        var neg: List<Int>
        nums.groupBy { it >= 0 }.let {
            pos = it[true]?.sorted() ?: emptyList()
            neg = it[false]?.sorted() ?: emptyList()
        }
        var ans = 0
        if (k <= neg.size) {
            ans += pos.sum()
            for (i in neg.indices) {
                if (i in 0 until k) {
                    ans -= neg[i]
                } else {
                    ans += neg[i]
                }
            }
            return ans
        }
        return if ((k - neg.size) % 2 == 0) {
            ans += pos.sum()
            ans -= neg.sum()
            ans
        } else {
            ans += pos.sum()
            ans -= neg.sum()
            ans -= nums.map { abs(it) }.sorted()[0] * 2
            ans
        }
    }
}