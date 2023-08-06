package leetcode.contest.c357

import utils.dir4
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()

    s.maximumSafenessFactor("[[0,1,1],[0,1,1],[1,1,1]]".toGrid().map { it.toList() }).print()
}

class SolutionC {
    fun maximumSafenessFactor(grid: List<List<Int>>): Int {
        val n = grid.size
        val m = grid[0].size

        val pos = ArrayList<Pair<Int, Int>>()
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (grid[i][j] == 1) {
                    pos.add(Pair(i, j))
                }
            }
        }

        val dis = Array<IntArray>(n) { IntArray(m) { Int.MAX_VALUE / 2 } }
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        pos.forEach {
            queue.offer(it)
        }
        val seen0 = HashSet<Pair<Int, Int>>()
        var step = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (i in 0 until size) {
                val item = queue.poll()
                val x = item.first
                val y = item.second
                if (dis[x][y] == Int.MAX_VALUE / 2)
                    dis[x][y] = step
                dir4.forEach {
                    val nextX = x + it[0]
                    val nextY = y + it[1]
                    if (nextX in 0 until n && nextY in 0 until m && Pair(nextX, nextY) !in seen0) {
                        queue.offer(Pair(nextX, nextY))
                        seen0.add(Pair(nextX, nextY))
                    }
                }
            }
        }
        dis.print()

        val seen = HashSet<Pair<Int, Int>>()
        val pq: PriorityQueue<Triple<Int, Int, Int>> = PriorityQueue<Triple<Int, Int, Int>>(compareBy { -it.third })
        pq.offer(Triple(0, 0, dis[0][0]))
        while (pq.isNotEmpty()) {
            val item = pq.poll()
            if (item.first == n - 1 && item.second == m - 1) return item.third
            dir4.forEach {
                val nextX = item.first + it[0]
                val nextY = item.second + it[1]
                if (nextX in 0 until n && nextY in 0 until m && Pair(nextX, nextY) !in seen) {
                    pq.offer(Triple(nextX, nextY, minOf(item.third, dis[nextX][nextY])))
                    seen.add(Pair(nextX, nextY))
                }
            }
        }

        return 0
    }
}