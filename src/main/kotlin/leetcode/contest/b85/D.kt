package leetcode.contest.b85

import utils.UFS
import utils.print
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.maximumSegmentSum(intArrayOf(1, 2, 5, 6, 1), intArrayOf(0, 3, 2, 4, 1)).print()
}

class SolutionD {
    fun maximumSegmentSum(nums: IntArray, removeQueries: IntArray): LongArray {
        val ans = ArrayList<Long>()
        val ufs = UFS(nums.size)
        var cur = 0L
        val sum = LongArray(nums.size)

        val seen = HashSet<Int>()
        ans.add(0)
        removeQueries.reverse()
        for (i in 0 until removeQueries.size - 1) {
            val index = removeQueries[i]
            seen.add(index)
            var a = -1
            if (index - 1 in seen) {
                a = ufs.find(index - 1)
                ufs.union(index, index - 1)
            }
            var b = -1
            if (index + 1 in seen) {
                b = ufs.find(index + 1)
                ufs.union(index, index + 1)
            }
            val c = ufs.find(index)
            sum[c] = if (a in sum.indices) {
                sum[a]
            } else {
                0
            } + if (b in sum.indices) {
                sum[b]
            } else {
                0
            } + nums[index]
            cur = maxOf(cur, sum[c])
            ans.add(cur)
        }
        return ans.reversed().toLongArray()
    }
}