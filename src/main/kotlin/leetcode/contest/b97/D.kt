package leetcode.contest.b97

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
//    s.isPossibleToCutPath("[[1,1,1],[1,0,0],[1,1,1]]".toGrid()).print()
//    s.isPossibleToCutPath("[[1,1,1],[1,1,1],[1,1,1]]".toGrid()).print()
    s.isPossibleToCutPath("[[1,1,1],[1,0,1],[1,1,1]]".toGrid()).print()
    s.isPossibleToCutPath("[[1,0,0],[0,0,0],[1,1,1]]".toGrid()).print()
}

class SolutionD {
    fun isPossibleToCutPath(grid: Array<IntArray>): Boolean {
        val dir2 = arrayOf(
            intArrayOf(0, 1),
            intArrayOf(1, 0)
        )

        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        queue.offer(Pair(0, 0))
        val seen = HashSet<Pair<Int, Int>>()
        var step = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            if (size == 1) {
                queue.peek().let {
                    if (it.first != 0 || it.second != 0) return !(it.first == grid.lastIndex && it.second == grid[0].lastIndex)
                }
            }
            for (i in 0 until size) {
                val item = queue.poll()
                dir2.forEach {
                    val x = item.first + it[0]
                    val y = item.second + it[1]
                    if (x in grid.indices && y in grid[0].indices && grid[x][y] == 1 && Pair(x, y) !in seen) {
                        queue.offer(Pair(x, y).also {
                            seen.add(it)
                        })
                    }
                }
            }
        }
        if (step != grid.size + grid[0].size - 1) return true
        return false
    }
}