package leetcode.contest.b161

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionB()
    s.countIslands("[[0,2,1,0,0],[0,5,0,0,5],[0,0,1,0,0],[0,1,4,7,0],[0,2,0,0,8]]".toGrid(), 5).print()
    s.countIslands("[[3,0,3,0],[0,3,0,3],[3,0,3,0]]".toGrid(), 3).print()
}

class SolutionB {
    fun countIslands(grid: Array<IntArray>, k: Int): Int {

        val m = grid.size
        val n = grid[0].size
        val seen = HashSet<Pair<Int, Int>>()
        var ans = 0

        for (i in 0 until m) {
            for (j in 0 until n) {
                val key = Pair(i, j)
                if (key !in seen && grid[i][j] > 0) {
                    var sum = 0L
                    val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
                    queue.add(Pair(i, j))
                    seen.add(key)

                    while (queue.isNotEmpty()) {
                        val (r, c) = queue.poll()
                        sum += grid[r][c]

                        for ((dr, dc) in dir4) {
                            val nr = r + dr
                            val nc = c + dc
                            val nextKey = Pair(nr, nc)
                            if (nr in 0 until m && nc in 0 until n && nextKey !in seen && grid[nr][nc] > 0) {
                                seen.add(nextKey)
                                queue.add(Pair(nr, nc))
                            }
                        }
                    }

                    if (sum % k == 0L) {
                        ans++
                    }
                }
            }
        }

        return ans
    }
}