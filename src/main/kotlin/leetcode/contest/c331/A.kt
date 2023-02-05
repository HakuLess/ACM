package leetcode.contest.c331

import java.util.*
import kotlin.math.sqrt

class SolutionA {
    fun pickGifts(gifts: IntArray, k: Int): Long {
        val pq = PriorityQueue<Int>(compareBy { -it })
        gifts.forEach {
            pq.offer(it)
        }
        repeat(k) {
            val item = pq.poll()
            pq.offer(sqrt(item.toDouble()).toInt())
        }
        var ans = 0L
        while (pq.isNotEmpty()) {
            ans += pq.poll()
        }
        return ans
    }
}