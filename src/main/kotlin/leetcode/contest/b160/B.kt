package leetcode.contest.b160

import java.util.*

class SolutionB {
    fun minCost(m: Int, n: Int, waitCost: Array<IntArray>): Long {
        // curCost x y
        data class State(val cost: Long, val x: Int, val y: Int, val isWaiting: Int)

        val dir = arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))

        val dist = Array(m) { Array(n) { LongArray(2) { Long.MAX_VALUE / 2 } } }

        val pq = PriorityQueue<State>(compareBy { it.cost })
        dist[0][0][1] = 1
        pq.offer(State(1, 0, 0, 1))

        while (pq.isNotEmpty()) {
            val (curCost, x, y, isWaiting) = pq.poll()

            if (curCost > dist[x][y][isWaiting]) continue

            if (x == m - 1 && y == n - 1) return curCost

            // 正在等待，选择下一步进入
            if (isWaiting == 1) {
                for ((dx, dy) in dir) {
                    val nx = x + dx
                    val ny = y + dy
                    if (nx in 0 until m && ny in 0 until n) {
                        val enterCost = (nx + 1) * (ny + 1)
                        val nextCost = curCost + enterCost
                        if (nextCost < dist[nx][ny][0]) {
                            dist[nx][ny][0] = nextCost
                            pq.offer(State(nextCost, nx, ny, 0))
                        }
                    }
                }
            } else {
                // 刚进入，需要等待
                val wait = waitCost[x][y]
                val nextCost = curCost + wait
                if (nextCost < dist[x][y][1]) {
                    dist[x][y][1] = nextCost
                    pq.offer(State(nextCost, x, y, 1))
                }
            }
        }

        return -1
    }
}