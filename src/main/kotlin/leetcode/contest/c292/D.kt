package leetcode.contest.c292

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SolutionD {
    fun hasValidPath(grid: Array<CharArray>): Boolean {
        val n = grid.size
        val m = grid[0].size
        if ((n + m) % 2 == 0) return false

        // 当前值
        val map = HashMap<String, HashSet<Int>>()
        map["0,0"] = hashSetOf(1)
        // 坐标
        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        queue.offer(Pair(0, 0))
        val seen = HashSet<String>()
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                val key = "${item.first},${item.second}"

                if (item.first == grid.lastIndex && item.second == grid[0].lastIndex && 0 in map[key]!!)
                    return true

                arrayOf(
                    intArrayOf(1, 0),
                    intArrayOf(0, 1)
                ).forEach {
                    val nextX = item.first + it[0]
                    val nextY = item.second + it[1]
                    if (nextX in grid.indices && nextY in grid[0].indices) {
                        val nextKey = "${nextX},${nextY}"
                        map[nextKey] = map.getOrDefault(nextKey, hashSetOf())
                        map[key]!!.filter { it >= 0 }.forEach {
                            map[nextKey]!!.add(it + if (grid[nextX][nextY] == '(') 1 else -1)
                        }
                        if (nextKey !in seen) {
                            queue.offer(Pair(nextX, nextY))
                            seen.add(nextKey)
                        }
                    }
                }
            }
        }
        return false
    }
}