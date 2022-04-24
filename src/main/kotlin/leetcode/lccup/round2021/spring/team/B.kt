package leetcode.lccup.round2021.spring.team

import utils.dir4
import utils.print
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = SolutionB()
    s.conveyorBelt(arrayOf(">>v", "v^<", "<><"), intArrayOf(0, 1), intArrayOf(2, 0)).print()
    s.conveyorBelt(arrayOf(">>v", ">>v", "^<<"), intArrayOf(0, 0), intArrayOf(1, 1)).print()
    s.conveyorBelt(arrayOf(">^^>", "<^v>", "^v^<"), intArrayOf(0, 0), intArrayOf(1, 3)).print()
}

class SolutionB {
    fun conveyorBelt(matrix: Array<String>, start: IntArray, end: IntArray): Int {
        // 施法次数 x y
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        pq.offer(Triple(0, start[0], start[1]))
        val seen = HashSet<Int>()
        seen.add(start[0] + start[1] * 1000 + 0 * 1000000)

        while (pq.isNotEmpty()) {
            val (t, x, y) = pq.poll()

            if (x == end[0] && y == end[1]) {
                return t
            }
            var nextX = x
            var nextY = y
            when (matrix[x][y]) {
                '>' -> {
                    nextY++
                }
                '<' -> {
                    nextY--
                }
                'v' -> {
                    nextX++
                }
                '^' -> {
                    nextX--
                }
            }
            var key = nextX + nextY * 1000 + t * 1000000
            if (nextX in matrix.indices &&
                nextY in matrix[0].indices &&
                key !in seen
            ) {
                seen.add(key)
                pq.offer(Triple(t, nextX, nextY))
            }

            dir4.forEach {
                nextX = x + it[0]
                nextY = y + it[1]

                key = nextX + nextY * 1000 + (t + 1) * 1000000

                if (nextX in matrix.indices &&
                    nextY in matrix[0].indices &&
                    key !in seen
                ) {
                    seen.add(key)
                    pq.offer(Triple(t + 1, nextX, nextY))
                }
            }
        }
        return -1
    }
}