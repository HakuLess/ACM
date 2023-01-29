package leetcode.contest.c330

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionD()
    // 2
    s.countQuadruplets(intArrayOf(1, 3, 2, 4, 5)).print()
//    s.countQuadruplets(intArrayOf(1, 2, 3, 4)).print()
//
//    // 0
//    s.countQuadruplets(intArrayOf(2, 5, 3, 1, 4)).print()
    // 1
    s.countQuadruplets(intArrayOf(1, 3, 5, 2, 4)).print()
}

class SolutionD {
    fun countQuadruplets(nums: IntArray): Long {
        val left = SegmentTree<Long>(0, 10000, 0) { a, b -> a + b }

        var ans = 0L

        for (i in nums.indices) {
            val item = nums[i]
            left.update(left, item, left.query(left, item, item) + 1)
            val right = SegmentTree<Long>(0, 10000, 0) { a, b -> a + b }

            for (j in nums.lastIndex downTo i + 1) {
                val item1 = nums[j]
                right.update(right, item1, right.query(right, item1, item1) + 1)

                if (item1 < item) {
                    // 在i左侧找 小于j 的
                    val l = left.query(left, 0, item1 - 1)
                    // 在j右侧找 大于i 的
                    val r = right.query(right, item + 1, 10000)
                    ans += l * r

//                    right.print { it != 0L }
//                    println("$i $j with $l $r")
                }
            }
        }
        return ans
    }
}