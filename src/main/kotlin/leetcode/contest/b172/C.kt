package leetcode.contest.b172

import utils.print
import java.util.PriorityQueue

fun main() {
    val s = SolutionC()
    // 7
    s.maximumScore(intArrayOf(2, 1, 5, 2, 3), "01010").print()
}

class SolutionC {
    fun maximumScore(nums: IntArray, s: String): Long {
        val pos = ArrayList<Int>()
        for (i in s.indices) {
            if (s[i] == '1') pos.add(i)
        }

        if (pos.isEmpty()) return 0L

        val maxHeap = PriorityQueue<Int>(compareByDescending { it })

        var sum = 0L
        var i = 0

        for (j in pos.indices) {
            while (i <= pos[j]) {
                maxHeap.offer(nums[i])
                i++
            }
            sum += maxHeap.poll()
        }

        return sum
    }
}