package leetcode.contest.c413

import java.util.*
import kotlin.math.abs

class SolutionB {
    fun resultsArray(queries: Array<IntArray>, k: Int): IntArray {

        val maxHeap = PriorityQueue<Int>(compareByDescending { it })
        val results = IntArray(queries.size)

        for (i in queries.indices) {
            val (x, y) = queries[i]
            val distance = abs(x) + abs(y)

            maxHeap.add(distance)

            if (maxHeap.size > k) {
                maxHeap.poll()
            }

            results[i] = if (maxHeap.size == k) maxHeap.peek() else -1
        }

        return results
    }
}