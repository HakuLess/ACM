package leetcode.contest.b139

import utils.print
import java.util.*

fun main() {
    val s = SolutionB()
    s.findSafeWalk(listOf(listOf(1, 1, 1, 1)), 4).print()
}

class SolutionB {
    fun findSafeWalk(grid: List<List<Int>>, health: Int): Boolean {
        val m = grid.size
        val n = grid[0].size

        val directions = arrayOf(
            intArrayOf(-1, 0),
            intArrayOf(1, 0),
            intArrayOf(0, -1),
            intArrayOf(0, 1)
        )

        val visited = Array(m) { IntArray(n) { -1 } }

        val queue: Queue<Position> = LinkedList()
        val start = health - if (grid[0][0] == 1) {
            1
        } else {
            0
        }
        queue.offer(Position(0, 0, start))
        visited[0][0] = start

        while (queue.isNotEmpty()) {
            val curr = queue.poll()

//            println("enter $curr")
            if (curr.x == m - 1 && curr.y == n - 1 && curr.health > 0) {
//                visited.print()
                return true
            }

            for (dir in directions) {
                val newX = curr.x + dir[0]
                val newY = curr.y + dir[1]

                if (newX in 0 until m && newY in 0 until n) {
                    val newHealth = curr.health - grid[newX][newY]

                    if (newHealth > 0 && newHealth > visited[newX][newY]) {
                        queue.offer(Position(newX, newY, newHealth))
//                        println("offer ${Position(newX, newY, newHealth)}")
                        visited[newX][newY] = newHealth
                    }
                }
            }
        }

        return false
    }
}

data class Position(val x: Int, val y: Int, val health: Int)