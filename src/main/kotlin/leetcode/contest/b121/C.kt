package leetcode.contest.b121

import java.util.*
import kotlin.collections.HashSet

class SolutionC {
    fun minimumOperationsToMakeEqual(x: Int, y: Int): Int {
        val queue: Queue<Int> = LinkedList<Int>()
        queue.offer(x)
        val set = HashSet<Int>()
        var step = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (i in 0 until size) {
                val item = queue.poll()
                if (item == y) return step
                if (item % 11 == 0 && item / 11 !in set) {
                    set.add(item / 11)
                    queue.offer(item / 11)
                }
                if (item % 5 == 0 && item / 5 !in set) {
                    set.add(item / 5)
                    queue.offer(item / 5)
                }
                if (item - 1 !in set) {
                    set.add(item - 1)
                    queue.offer(item - 1)
                }
                if (item + 1 !in set) {
                    set.add(item + 1)
                    queue.offer(item + 1)
                }
            }
        }
        return -1
    }
}