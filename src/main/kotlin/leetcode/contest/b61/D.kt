package leetcode.contest.b61

import utils.SegmentTree
import utils.print

fun main() {
    val s = Solution5862()
    s.minOperations(intArrayOf(4, 2, 5, 3)).print()
//    s.minOperations(intArrayOf(1, 2, 3, 5, 6)).print()
//    s.minOperations(intArrayOf(1, 10, 100, 1000)).print()
    s.minOperations(intArrayOf(8, 5, 9, 9, 8, 4)).print()
}

class Solution5862 {
    fun minOperations(nums: IntArray): Int {
        val n = nums.size
        val min = nums.minOrNull()!!
//        val min = nums.min()!!
        val max = nums.maxOrNull()!!
//        val max = nums.max()!!
        val root = SegmentTree<Int>(start = min, end = max, value = 0) { a, b ->
            a + b
        }
        nums.sort()
        nums.forEach {
            root.update(root, it, 1)
        }
        var res = n
        nums.forEach {
            val ans = root.query(root, it, it + n - 1)
//            println("$it ${it + n - 1} is $ans")
            res = minOf(res, n - ans)
        }
        return res
    }
}