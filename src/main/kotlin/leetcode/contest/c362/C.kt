package leetcode.contest.c362

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class SolutionC {
    fun minimumMoves(grid: Array<IntArray>): Int {
        val indexMap = hashMapOf<Int, IntArray>(
            0 to intArrayOf(1, 3),
            1 to intArrayOf(0, 2, 4),
            2 to intArrayOf(1, 5),
            3 to intArrayOf(0, 4, 6),
            4 to intArrayOf(1, 3, 5, 7),
            5 to intArrayOf(2, 4, 8),
            6 to intArrayOf(3, 7),
            7 to intArrayOf(4, 6, 8),
            8 to intArrayOf(5, 7)
        )
        val queue: Queue<ArrayList<Int>> = LinkedList<ArrayList<Int>>()
        val init = ArrayList<Int>()
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                init.add(grid[i][j])
            }
        }
        queue.offer(init)
        val seen = HashSet<String>()
        seen.add(init.joinToString())
        var step = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.all { it == 1 }) return step
                val moveIndex = item.indexOfFirst { it > 1 }
                indexMap[moveIndex]!!.forEach {
                    val next = item.clone() as ArrayList<Int>
                    next[moveIndex]--
                    next[it]++
                    if (next.joinToString() !in seen) {
                        seen.add(next.joinToString())
                        queue.offer(next)
                    }
                }
            }
        }
        return step
    }
}