package leetcode.contest.b72

import utils.MultiSet
import utils.print

fun main() {
    val s = SolutionD()
    s.goodTriplets(intArrayOf(2, 0, 1, 3), intArrayOf(0, 1, 2, 3)).print()
//    s.goodTriplets(intArrayOf(4, 0, 1, 3, 2), intArrayOf(4, 1, 0, 2, 3)).print()
}

class SolutionD {
    fun goodTriplets(nums1: IntArray, nums2: IntArray): Long {
        val keys = HashMap<Int, Int>()
        var tmp = 0
        nums2.forEach {
            keys[it] = tmp
            tmp++
        }
        val n = nums1.size
        val multiSet = MultiSet<Int>()
        var ans = 0L
        for (i in nums1.indices) {
            val cur = keys[nums1[i]]!!
            multiSet.add(cur)
            val index = multiSet.valueList.binarySearch(cur)
            val size = multiSet.valueList.size
            ans += index.toLong() * (n - cur - (size - index))
        }
        return ans
    }
}