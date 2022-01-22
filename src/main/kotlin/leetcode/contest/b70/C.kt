package leetcode.contest.b70

import utils.dir4
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

class SolutionC {
    fun highestRankedKItems(grid: Array<IntArray>, pricing: IntArray, start: IntArray, k: Int): List<List<Int>> {
        // x,y,step
        val ans = ArrayList<Triple<Int, Int, Int>>()
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        queue.offer(Pair(start[0], start[1]))
        var step = 0
        val seen = HashSet<Pair<Int, Int>>()
        seen.add(Pair(start[0], start[1]))
        while (queue.isNotEmpty() && ans.size < k) {
            step++
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.first !in grid.indices || item.second !in grid[0].indices || grid[item.first][item.second] == 0)
                    continue
                if (grid[item.first][item.second] in pricing[0]..pricing[1]) {
                    ans.add(Triple(item.first, item.second, step))
                }
                dir4.forEach {
                    val next = Pair(item.first + it[0], item.second + it[1])
                    if (next !in seen) {
                        queue.offer(next)
                        seen.add(next)
                    }
                }
            }
        }
        ans.sortWith(compareBy({ it.third }, { grid[it.first][it.second] }, { it.first }, { it.second }))
        return ans.take(k).map {
            listOf(it.first, it.second)
        }
    }
}