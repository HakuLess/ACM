package leetcode.contest.c386

import utils.biMin
import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
    s.earliestSecondToMarkIndices(intArrayOf(2, 2, 0), intArrayOf(2, 2, 2, 2, 3, 2, 2, 1)).print()
    s.earliestSecondToMarkIndices(intArrayOf(1, 3), intArrayOf(1, 1, 1, 2, 1, 1, 1)).print()
    // 10
    s.earliestSecondToMarkIndices(intArrayOf(0, 2, 3, 0), intArrayOf(2, 4, 1, 3, 3, 3, 3, 3, 3, 2, 1)).print()
    s.earliestSecondToMarkIndices(intArrayOf(0, 1), intArrayOf(2, 2, 2)).print()
}

class SolutionC {
    fun earliestSecondToMarkIndices(nums: IntArray, changeIndices: IntArray): Int {
        val n = nums.size
        val m = changeIndices.size
        return biMin(l = 1L, r = m.toLong()) {
            val arr = IntArray(n + 1) { -1 }
            arr[0] = 0
            var min = 0
            for (i in 0 until it.toInt()) {
                arr[changeIndices[i]] = i + 1
                if (min == 0 && arr.all { it != -1 }) {
                    min = i + 1
                }
            }

            val p = ArrayList<Pair<Int, Int>>()
            for (i in 0 until it.toInt()) {
                p.add(Pair(changeIndices[i], i + 1))
            }

//        arr.joinToString().print()
            if (arr.any { it == -1 }) return@biMin false

            // 值 位次
            val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
            for (i in 1 until arr.size) {
                pq.add(Pair(i, arr[i]))
            }

            var cur = 0
            while (pq.isNotEmpty()) {
                val item = pq.poll()
                val index = item.first
                val needBefore = item.second

//                println("poll $item with $cur ${nums[index - 1]}")

                cur += nums[index - 1] + 1
//            println("$cur with $needBefore")
                if (cur > needBefore) return@biMin false
            }

            return@biMin true
        }.toInt()
    }
}