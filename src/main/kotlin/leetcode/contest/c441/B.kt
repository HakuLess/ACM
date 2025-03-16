package leetcode.contest.c441

import utils.print
import utils.printInt
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionB()
    // 2,-1,3
    s.solveQueries(intArrayOf(1, 3, 1, 4, 1, 3, 2), intArrayOf(0, 3, 5)).joinToString().print()

    // [-1,1,1]
    s.solveQueries(intArrayOf(2, 10, 20, 20, 20), intArrayOf(1, 4, 2)).joinToString().print()

    // [-1,1,1,2,-1]
    s.solveQueries(intArrayOf(14, 14, 4, 2, 19, 19, 14, 19, 14), intArrayOf(2, 4, 8, 6, 3)).joinToString().print()
}

class SolutionB {
    fun solveQueries(nums: IntArray, queries: IntArray): List<Int> {
        val n = nums.size
        val indexMap = HashMap<Int, TreeSet<Int>>()

        for (i in nums.indices) {
            indexMap.computeIfAbsent(nums[i]) { TreeSet() }.add(i)
        }

        val ans = ArrayList<Int>()
        for (i in queries.indices) {
            val q = queries[i]
            val target = nums[q]

            val left = indexMap[target]!!.lower(q)
            val right = indexMap[target]!!.higher(q)
            val min = indexMap[target]!!.first()!!
            val max = indexMap[target]!!.last()!!

            // println("$q: $target with $left $right $min $max")

            var dis = Int.MAX_VALUE
            if (left != null) {
                dis = minOf(dis, minOf(q - left), minOf(n - (q - left)))
            }
            if (right != null) {
                dis = minOf(dis, minOf(right - q, n - (right - q)))
            }
            if (min != q) {
                dis = minOf(dis, minOf(q - min), minOf(n - (q - min)))
            }
            if (max != q) {
                dis = minOf(dis, minOf(max - q, n - (max - q)))
            }
            if (dis == Int.MAX_VALUE) {
                dis = -1
            }
            ans.add(dis)
        }
        return ans
    }
}