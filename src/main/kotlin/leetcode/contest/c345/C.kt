package leetcode.contest.c345

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    s.maxMoves("[[2,4,3,5],[5,4,9,3],[3,4,2,11],[10,9,13,15]]".toGrid()).print()
    s.maxMoves("[[3,2,4],[2,1,9],[1,1,7]]".toGrid()).print()
}

class SolutionC {
    fun maxMoves(grid: Array<IntArray>): Int {

        val seen = HashSet<Pair<Int, Int>>()

        fun bfs(x: Int, y: Int): Int {
            val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
            queue.offer(Pair(x, y))
            var step = -1
            while (queue.isNotEmpty()) {
                val size = queue.size
                step++
                for (i in 0 until size) {
                    val (x, y) = queue.poll()
                    dir3.forEach {
                        val next = Pair(x + it[0], y + it[1])
                        if (next.first in grid.indices && next.second in grid[0].indices
                            && grid[next.first][next.second] > grid[x][y]
                            && next !in seen
                        ) {
                            queue.offer(next)
                            seen.add(next)
                        }
                    }
                }
            }
            return step
        }

        var ans = 0
        for (i in grid.indices) {
            ans = maxOf(ans, bfs(i, 0))
        }
        return ans
    }
}

val dir3 = arrayOf(
    intArrayOf(-1, 1),
    intArrayOf(0, 1),
    intArrayOf(1, 1)
)