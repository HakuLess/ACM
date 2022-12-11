package leetcode.contest.c323

import utils.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.maxPoints("[[1,2,3],[2,5,7],[3,5,1]]".toGrid(), intArrayOf(5, 6, 2)).print()
}

class SolutionD {
    fun maxPoints(grid: Array<IntArray>, queries: IntArray): IntArray {
        val map = HashMap<Int, Int>()

        val queue: PriorityQueue<Triple<Int, Int, Int>> = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        queue.offer(Triple(0, 0, grid[0][0]))
        val seen = HashSet<Int>()
        while (queue.isNotEmpty()) {
            val item = queue.poll()
            val key = item.first * 2000 + item.second
            if (key in seen) continue
            seen.add(key)
            map[key] = item.third
            dir4.forEach {
                val nextX = item.first + it[0]
                val nextY = item.second + it[1]
                if (nextX in grid.indices && nextY in grid[0].indices) {
                    val nextV = maxOf(item.third, grid[nextX][nextY])
                    queue.offer(Triple(nextX, nextY, nextV))
                }
            }
        }

        val ansMap = HashMap<Int, Int>()
        var i = 0
        val sorted = queries.sorted()
        var cur = 0
        map.values.sorted().forEach {
            while (i in sorted.indices) {
                if (sorted[i] <= it) {
                    i++
                } else {
                    break
                }
            }
            if (i in sorted.indices) {
                cur++
                ansMap[sorted[i]] = cur
            }
        }

        var c = 0
        sorted.forEach {
            if (it in ansMap.keys) {
                c = ansMap[it]!!
            } else {
                ansMap[it] = c
            }
        }

        return queries.map {
            ansMap.getOrDefault(it, 0)
        }.toIntArray()
    }
}