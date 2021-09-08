package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val s = Solution502()
//    s.findMaximizedCapital(2, 0, intArrayOf(1, 2, 3), intArrayOf(0, 1, 1)).print()
    s.findMaximizedCapital(1, 2, intArrayOf(1, 2, 3), intArrayOf(1, 1, 2)).print()
}

class Solution502 {
    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        val n: Int = profits.size
        var curW = w

        // 成本 利润
        val arr = ArrayList<Pair<Int, Int>>()
        for (i in 0 until n) {
            arr.add(Pair(capital[i], profits[i]))
        }
        arr.sortBy { it.first }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { -it.second })
        var index = 0
        for (i in 0 until k) {
            while (index in arr.indices && arr[index].first <= curW) {
                pq.offer(arr[index])
                index++
            }
            if (pq.isEmpty()) return curW
            curW += pq.poll().second
        }
        return curW
    }
}