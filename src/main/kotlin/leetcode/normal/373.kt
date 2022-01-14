package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = Solution373()
    s.kSmallestPairs(intArrayOf(1, 7, 11), intArrayOf(2, 4, 6), 3).print()
}

class Solution373 {
    fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { nums1[it.first] + nums2[it.second] })
        for (i in 0 until minOf(nums1.size, k)) {
            pq.offer(Pair(i, 0))
        }
        val ans = ArrayList<List<Int>>()
        while (ans.size != k && pq.isNotEmpty()) {
            val cur = pq.poll()
            ans.add(listOf(nums1[cur.first], nums2[cur.second]))
            if (cur.second + 1 in nums2.indices)
                pq.offer(Pair(cur.first, cur.second + 1))
        }
        return ans
    }
}