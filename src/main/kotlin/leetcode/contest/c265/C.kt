package leetcode.contest.c265

import java.util.*
import kotlin.collections.HashSet

class Solution2059 {
    fun minimumOperations(nums: IntArray, start: Int, goal: Int): Int {
        val queue: Queue<Int> = LinkedList<Int>()
        queue.add(start)
        var step = 0
        val seen = HashSet<Int>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (k in 0 until size) {
                val item = queue.poll()
                if (item == goal) return step
                if (item in seen) continue
                if (item !in seen) seen.add(item)
                if (item !in 0..1000) continue
                nums.forEach {
                    queue.offer(item + it)
                    queue.offer(item - it)
                    queue.offer(item xor it)
                }
            }
        }
        return -1
    }
}