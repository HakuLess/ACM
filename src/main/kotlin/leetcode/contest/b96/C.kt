package leetcode.contest.b96

import utils.MultiSet
import utils.SegmentTree
import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionC()
//    s.maxScore(intArrayOf(1, 3, 3, 2), intArrayOf(2, 1, 3, 4), 3).print()
    // 30
//    s.maxScore(intArrayOf(4, 2, 3, 1, 1), intArrayOf(7, 5, 10, 9, 6), 1).print()
    // 168
    s.maxScore(intArrayOf(2, 1, 14, 12), intArrayOf(11, 7, 13, 6), 3).print()
}

class SolutionC {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val ids = nums1.indices.sortedByDescending { nums2[it] }

        val p1 = PriorityQueue<Int>(compareBy { it })
        // 队列2塞个0，方便比较
        val p2 = PriorityQueue<Int>(compareBy { -it })
        p2.offer(0)

        var sum = 0L
        var ans = 0L
        for (i in ids) {
            val a = nums2[i].toLong()

            val peek = p2.poll()
            // 大的加入当前
            val add = maxOf(nums1[i], peek)
            p1.offer(add)
            sum += add

            // 小的扔到备选
            val min = minOf(nums1[i], peek)
            p2.offer(min)

            if (p1.size > k) {
                val item = p1.poll()
                p2.offer(item)
                sum -= item
            }

            if (p1.size == k) {
                ans = maxOf(ans, a * sum)
            }
        }
        return ans
    }
}