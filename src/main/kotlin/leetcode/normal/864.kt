package leetcode.normal

import utils.dir4
import utils.print
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = Solution864()
    s.shortestPathAllKeys(arrayOf("@.a.#", "###.#", "b.A.B")).print()
}

class Solution864 {
    fun shortestPathAllKeys(grid: Array<String>): Int {

        var total = 0
        val queue = LinkedList<Triple<Int, Int, String>>()
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '@') {
                    queue.offer(Triple(i, j, ""))
                } else if (grid[i][j] in 'a'..'z') {
                    total++
                }
            }
        }

        val seen = HashSet<Triple<Int, Int, String>>()
        var step = -1
        while (queue.isNotEmpty()) {
            step++
            for (i in 0 until queue.size) {
                val (x, y, keys) = queue.poll()
                if (keys.length == total) {
                    return step
                }
                dir4.forEach {
                    val nextX = x + it[0]
                    val nextY = y + it[1]
                    var next: Triple<Int, Int, String>? = null
                    if (nextX in grid.indices && nextY in grid[0].indices) {
                        if (grid[nextX][nextY] in 'a'..'z') {
                            val nextKeys = (keys + grid[nextX][nextY]).toSortedSet().joinToString("")
                            next = Triple(x + it[0], y + it[1], nextKeys)
                        } else if (grid[nextX][nextY] in 'A'..'Z') {
                            if (grid[nextX][nextY].toLowerCase() in keys) {
                                next = Triple(x + it[0], y + it[1], keys)
                            }
                        } else if (grid[nextX][nextY] == '#') {

                        } else {
                            next = Triple(x + it[0], y + it[1], keys)
                        }
                    }
                    if (next != null && next !in seen) {
                        seen.add(next)
                        queue.offer(next)
                    }
                }
            }
        }
        return -1
    }
}