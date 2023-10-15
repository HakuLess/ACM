package leetcode.contest.c367

import utils.SegmentTree
import utils.print

fun main() {
    val s = SolutionC()
    s.findIndices(intArrayOf(5, 1, 4, 1), 2, 4).print()
}

class SolutionC {
    fun findIndices(nums: IntArray, indexDifference: Int, valueDifference: Int): IntArray {
        val root = SegmentTree<Pair<Int, Int>>(start = 0, end = 100005, value = Pair(0, 0)) { a, b ->
            if (a.first >= b.first) a else b
        }
        for (i in nums.indices) {
            root.update(root, i, Pair(nums[i], i))
        }

        for (i in nums.indices) {
            val left = i - indexDifference
            if (left >= 0) {
                val max = root.query(root, 0, left)
                if (max.first - nums[i] >= valueDifference) {
                    return intArrayOf(max.second, i)
                }
            }
            val right = i + indexDifference
            if (right < nums.size) {
                val max = root.query(root, right, nums.lastIndex)
                if (max.first - nums[i] >= valueDifference) {
                    return intArrayOf(i, max.second)
                }
            }
        }
        return intArrayOf(-1, -1)
    }
}