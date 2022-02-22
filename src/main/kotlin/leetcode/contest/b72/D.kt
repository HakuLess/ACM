package leetcode.contest.b72

import utils.MultiSet
import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionD()
    s.goodTriplets(intArrayOf(2, 0, 1, 3), intArrayOf(0, 1, 2, 3)).print()
    // 0 1 2 3 4
    // 0 2 1 4 3
    s.goodTriplets(intArrayOf(4, 0, 1, 3, 2), intArrayOf(4, 1, 0, 2, 3)).print()
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
        val root = SegmentTree<Int>(start = 0, end = n - 1, value = 0) { a, b -> a + b }
        var ans = 0L
        for (i in nums1.indices) {
            val cur = keys[nums1[i]]!!
            // 一共 1~n-1，左侧小于当前值left，大于当前值right
            // 一共大于当前值n-cur-1个
            val left = root.query(root, 0, cur)
            val right = root.query(root, cur, n - 1)
            root.update(root, cur, 1)
            ans += 1L * left * (n - 1 - cur - right)
        }
        return ans
    }

//    fun goodTriplets(nums1: IntArray, nums2: IntArray): Long {
//        val keys = HashMap<Int, Int>()
//        var tmp = 0
//        nums2.forEach {
//            keys[it] = tmp
//            tmp++
//        }
//        val n = nums1.size
//        val multiSet = MultiSet<Int>()
//        var ans = 0L
//        for (i in nums1.indices) {
//            val cur = keys[nums1[i]]!!
//            multiSet.add(cur)
//            val index = multiSet.valueList.binarySearch(cur)
//            val size = multiSet.valueList.size
//            ans += index.toLong() * (n - cur - (size - index))
//        }
//        return ans
//    }
}