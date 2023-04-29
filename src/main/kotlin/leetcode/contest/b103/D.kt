package leetcode.contest.b103

import utils.SegmentTree
import utils.print
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
    s.countOperationsToEmptyArray(intArrayOf(3, 4, -1)).print()
    s.countOperationsToEmptyArray(intArrayOf(1, 2, 4, 3)).print()
    s.countOperationsToEmptyArray(intArrayOf(1, 2, 3)).print()
    s.countOperationsToEmptyArray(intArrayOf(-15, -19, 5)).print()
}

class SolutionD {
    fun countOperationsToEmptyArray(nums: IntArray): Long {
        val n = nums.size
        val sorted = nums.sorted()
        val map = HashMap<Int, Int>()
        for (i in sorted.indices) {
            map[sorted[i]] = i
        }

        val l = ArrayList<Int>()
        for (i in nums.indices) {
            l.add(map[nums[i]]!!)
        }

        val root = SegmentTree<Int>(start = 0, end = n, value = 0) { a, b ->
            a + b
        }

//        l.joinToString().print()
        val mapIndex = HashMap<Int, Int>()
        for (i in l.indices) {
            mapIndex[l[i]] = i
        }
        var ans = 0L
        var pre = 0
        for (i in 0 until n) {
            val next = mapIndex[i]!!
            val offset = if (next > pre) {
                root.query(root, pre, next)
            } else {
                root.query(root, 0, next) + root.query(root, pre, n)
            }
//            println("$next $pre with $offset")
            ans += (next + n - pre) % n - offset
            pre = next

            root.update(root, next, 1)
        }
        return ans + n
    }
}