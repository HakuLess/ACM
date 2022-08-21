package leetcode.contest.c307

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
//    s.kSum(intArrayOf(2, 4, -2), 5).print()
//    s.kSum(intArrayOf(1, -2, 3, 4, -10, 12), 16).print()
    s.kSum(intArrayOf(-1, 1), 1).print()
}

class SolutionD {
    fun kSum(nums: IntArray, k: Int): Long {
        var ans = 0L
        val pq = PriorityQueue<Pair<Long, Int>>(compareBy { it.first })
        val arr = ArrayList<Long>()
        nums.forEach {
            if (it > 0) {
                ans += it
                arr.add(it.toLong())
            } else {
                arr.add(-it.toLong())
            }
        }
        if (k == 1) return ans
        arr.sort()
        pq.offer(Pair(arr[0], 0))
        for (i in 0 until k - 2) {
            val item = pq.poll()
            if (item.second == nums.size - 1) continue
            // 以Pair.Second为结尾的子序列
            pq.offer(Pair(item.first + arr[item.second + 1], item.second + 1))
            pq.offer(Pair(item.first - arr[item.second] + arr[item.second + 1], item.second + 1))
        }
        return ans - pq.poll().first
    }
}