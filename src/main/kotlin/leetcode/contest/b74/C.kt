package leetcode.contest.b74

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.halveArray(intArrayOf(3, 8, 20)).print()
}

class SolutionC {
    fun halveArray(nums: IntArray): Int {
        var sum = 0.0
        val pq = PriorityQueue<Double>(compareBy { -it })
        for (i in nums.indices) {
            sum += nums[i]
            pq.offer(nums[i].toDouble())
        }
        var cur = 0.0
        var ans = 0
        while (pq.isNotEmpty() && cur < sum / 2) {
            val item = pq.poll()
            cur += item / 2
            pq.offer(item / 2)
            ans++
        }
        return ans
    }
}