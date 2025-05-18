package leetcode.contest.c450

import utils.dir4
import utils.print
import java.util.*
import kotlin.collections.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
    // 13
//    s.minMoves(arrayOf(".#...", ".#.#.", ".#.#.", "...#.")).print()
    // 1
//    s.minMoves(arrayOf(".A", "CA")).print()
    // 3
    s.minMoves(arrayOf("..C.", "C.A.")).print()
}

class SolutionC {

    data class Item(val x: Int, val y: Int, val step: Int)

//    fun minMoves(matrix: Array<String>): Int {
//        val m = matrix.size
//        val n = matrix[0].length
//
//        val portals = HashMap<Char, MutableList<Pair<Int, Int>>>()
//        for (i in 0 until m) {
//            for (j in 0 until n) {
//                val c = matrix[i][j]
//                if (c in 'A'..'Z') {
//                    portals.computeIfAbsent(c) { mutableListOf() }.add(Pair(i, j))
//                }
//            }
//        }
//
//        val deque: Deque<Triple<Int, Int, Int>> = java.util.ArrayDeque()
//        val visited = Array(m) { BooleanArray(n) }
//        val usedPortal = mutableSetOf<Char>()
//
//        deque.offerFirst(Triple(0, 0, 0))
//
//        val dir4 = arrayOf(
//            intArrayOf(0, 1),
//            intArrayOf(0, -1),
//            intArrayOf(1, 0),
//            intArrayOf(-1, 0)
//        )
//
//        while (deque.isNotEmpty()) {
//            val (x, y, step) = deque.pollFirst()
//
//            if (x == m - 1 && y == n - 1) return step
//            if (visited[x][y]) continue
//            visited[x][y] = true
//
//            val cell = matrix[x][y]
//            if (cell in 'A'..'Z' && cell !in usedPortal) {
//                usedPortal.add(cell)
//                for ((px, py) in portals[cell]!!) {
//                    if (!visited[px][py]) {
//                        deque.offerFirst(Triple(px, py, step))
//                    }
//                }
//            }
//
//            for ((dx, dy) in dir4) {
//                val nx = x + dx
//                val ny = y + dy
//                if (nx in 0 until m && ny in 0 until n && matrix[nx][ny] != '#' && !visited[nx][ny]) {
//                    deque.offerLast(Triple(nx, ny, step + 1))
//                }
//            }
//        }
//
//        return -1
//    }

    fun minMoves(matrix: Array<String>): Int {

        val portals = HashMap<Char, ArrayList<Pair<Int, Int>>>()

        val m = matrix.size
        val n = matrix[0].length
        for (i in 0 until m) {
            for (j in 0 until n) {
                val c = matrix[i][j]
                if (c in 'A'..'Z') {
                    portals.computeIfAbsent(c) { ArrayList() }.add(Pair(i, j))
                }
            }
        }

        // x y step
        val pq = PriorityQueue<Item>(compareBy({ it.step }))
        pq.offer(Item(0, 0, 0))

        val visited = Array(m) { BooleanArray(n) { false } }
//        visited[0][0] = true

        val usedPortal = HashSet<Char>()

        while (pq.isNotEmpty()) {
            val (x, y, step) = pq.poll()
//            println("enter $x $y $step")

            if (x == m - 1 && y == n - 1) return step

            if (visited[x][y]) continue
            visited[x][y] = true

            // portal
            val cell = matrix[x][y]
            if (cell in 'A'..'Z' && cell !in usedPortal) {
                usedPortal.add(cell)
//                println("$x $y with $cell with ${usedPortal}")

                for ((px, py) in portals[cell]!!) {
//                    println("$px $py with ${visited[px][py]}")

                    if (!visited[px][py]) {
//                        visited[px][py] = true
                        pq.offer(Item(px, py, step))
//                        println("offer B ${Item(px, py, step + 1, usePortal = true)}")
                    }
                }
            }

            dir4.forEach {
                val nx = x + it[0]
                val ny = y + it[1]
                if (nx in 0 until m && ny in 0 until n && !visited[nx][ny] && matrix[nx][ny] != '#') {
                    pq.offer(Item(nx, ny, step + 1))
//                    println("offer A ${Item(nx, ny, step + 1, usePortal = false)}")
                }
            }
        }

        return -1
    }
}