package leetcode.normal

import utils.SegmentTree
import utils.print

fun main() {
    val s = Solution683()
//    s.kEmptySlots(intArrayOf(1, 3, 2), 1).print()
//    s.kEmptySlots(intArrayOf(1, 2, 3), 1).print()
    s.kEmptySlots(intArrayOf(10, 1, 9, 3, 5, 7, 6, 4, 8, 2), 0).print()
}

class Solution683 {
    fun kEmptySlots(bulbs: IntArray, k: Int): Int {
        val start = 0
        val end = 100000
        val root = SegmentTree<Int>(start, end, 0) { a, b -> a + b }
        for (i in bulbs.indices) {
            root.update(root, bulbs[i], 1)
            var right = bulbs[i]
            var left = bulbs[i] - k - 1
            if (left in start..end) {
                val total = if (k == 0) 0 else root.query(root, left + 1, right - 1)
                if (total == 0 && root.query(root, left, left) == 1) {
                    return i + 1
                }
            }
            left = bulbs[i]
            right = bulbs[i] + k + 1
            if (right in start..end) {
                val total = if (k == 0) 0 else root.query(root, left + 1, right - 1)
                if (total == 0 && root.query(root, right, right) == 1) {
                    return i + 1
                }
            }
        }
        return -1
    }
}