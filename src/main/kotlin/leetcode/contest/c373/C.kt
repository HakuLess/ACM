package leetcode.contest.c373

import utils.SegmentTree
import utils.print
import kotlin.time.seconds

fun main() {
    val s = SolutionC()
    s.lexicographicallySmallestArray(intArrayOf(1, 5, 3, 9, 8), 2).print()
    s.lexicographicallySmallestArray(intArrayOf(1, 7, 6, 18, 2, 1), 2).print()
    s.lexicographicallySmallestArray(intArrayOf(1, 7, 28, 19, 10), 3).print()
    s.lexicographicallySmallestArray(intArrayOf(2, 2, 1, 28, 2, 1), 3).print()
}

class SolutionC {
    fun lexicographicallySmallestArray(nums: IntArray, limit: Int): IntArray {
        val n = nums.size
        val l = ArrayList<Pair<Int, Int>>()
        for (i in nums.indices) {
            l.add(Pair(i, nums[i]))
        }
        l.sortBy { it.second }
        val ans = IntArray(n)
        val ids = ArrayList<Int>()
        val vals = ArrayList<Int>()
        for (i in 0..l.size) {
            if (i == l.size || (i != 0 && l[i].second - l[i - 1].second > limit)) {
                ids.sort()
                vals.sort()
                for (i in ids.indices) {
                    ans[ids[i]] = vals[i]
                }
                ids.clear()
                vals.clear()
            }
            if (i in l.indices) {
                ids.add(l[i].first)
                vals.add(l[i].second)
            }
        }
        return ans
    }
}