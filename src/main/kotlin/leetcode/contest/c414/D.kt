package leetcode.contest.c414

import java.util.*
import kotlin.collections.HashMap

class SolutionD {
    fun maxMoves(kx: Int, ky: Int, positions: Array<IntArray>): Int {
        val posList = positions.map { Pair(it[0], it[1]) }
        return minimax(kx, ky, posList, true, mutableMapOf())
    }
}

val dx = intArrayOf(-2, -1, 1, 2, 2, 1, -1, -2)
val dy = intArrayOf(1, 2, 2, 1, -1, -2, -2, -1)

val seenMap = HashMap<String, Array<IntArray>>()

// 计算从某个起点到所有其他点的最短距离
fun bfs(kx: Int, ky: Int): Array<IntArray> {
    val key = "$kx, $ky"
    if (key in seenMap) return seenMap[key]!!

    val dist = Array(50) { IntArray(50) { Int.MAX_VALUE } }
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    dist[kx][ky] = 0
    queue.add(Pair(kx, ky))

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()
        for (i in 0 until 8) {
            val nx = x + dx[i]
            val ny = y + dy[i]
            if (nx in 0..49 && ny in 0..49 && dist[nx][ny] == Int.MAX_VALUE) {
                dist[nx][ny] = dist[x][y] + 1
                queue.add(Pair(nx, ny))
            }
        }
    }
    return dist.also {
        seenMap[key] = it
    }
}

// Minimax 博弈函数
fun minimax(
    kx: Int,
    ky: Int,
    positions: List<Pair<Int, Int>>,
    turn: Boolean,
    memo: MutableMap<Pair<Pair<Int, Int>, List<Pair<Int, Int>>>, Int>
): Int {
    if (positions.isEmpty()) return 0
    val state = Pair(Pair(kx, ky), positions)
    if (memo.containsKey(state)) return memo[state]!!

    var best = if (turn) Int.MIN_VALUE else Int.MAX_VALUE
    val dist = bfs(kx, ky)

    for ((i, pos) in positions.withIndex()) {
        val newPos = positions.toMutableList().apply { removeAt(i) }
        val steps = dist[pos.first][pos.second]
        val result = steps + minimax(pos.first, pos.second, newPos, !turn, memo)
        best = if (turn) maxOf(best, result) else minOf(best, result)
    }

    memo[state] = best
    return best
}