package leetcode.contest.b86

import utils.print
import java.util.*

fun main() {
    val s = SolutionD()
    s.maximumRobots(intArrayOf(3, 6, 1, 3, 4), intArrayOf(2, 1, 3, 4, 5), 25).print()
    s.maximumRobots(intArrayOf(11, 12, 19), intArrayOf(10, 8, 7), 19).print()
}

class SolutionD {
    fun maximumRobots(chargeTimes: IntArray, runningCosts: IntArray, budget: Long): Int {
        var l = 0
        var r = 0
        val pq = PriorityQueue<Long>(compareBy { -it })
        var sum = 0L
        var ans = 0
        while (r in chargeTimes.indices) {
            // 最右侧加入
            pq.offer(chargeTimes[r].toLong())
            var max = pq.peek()
            sum += runningCosts[r].toLong()
            var k = r - l + 1
            var cur = max + k * sum

            // 最左侧排出
            while (cur > budget) {
                if (l !in chargeTimes.indices) {
                    return ans
                }
                pq.remove(chargeTimes[l].toLong())
                max = if (pq.isNotEmpty()) {
                    pq.peek()
                } else {
                    0
                }
                sum -= runningCosts[l].toLong()
                l++
                k = r - l + 1
                cur = max + k * sum
            }
            ans = maxOf(ans, k)
            r++
        }
        return ans
    }
}