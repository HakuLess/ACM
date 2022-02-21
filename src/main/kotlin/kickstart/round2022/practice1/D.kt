package kickstart.round2022.practice1

import utils.dir6
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val n = readLine()!!.trim().toInt()
        val matrix = ArrayList<CharArray>()
        repeat(n) {
            val arr = readLine()!!.toCharArray()
            matrix.add(arr)
        }
        var b = 0
        var r = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (matrix[i][j] == 'B') {
                    b++
                } else if (matrix[i][j] == 'R') {
                    r++
                }
            }
        }

        if (b - r > 1 || b - r < -1) {
            println("Case #${it + 1}: Impossible")
            return@repeat
        }

        fun bfs(type: Int): Boolean {
            val seen = HashSet<Pair<Int, Int>>()
            val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
            val target = if (type == 0) 'B' else 'R'
            if (type == 0) {
                // Blue
                for (i in 0 until n) {
                    if (matrix[i][0] == 'B') {
                        queue.offer(Pair(i, 0))
                        seen.add(Pair(i, 0))
                    }
                }
            } else {
                // Red
                for (j in 0 until n) {
                    if (matrix[0][j] == 'R') {
                        queue.offer(Pair(0, j))
                        seen.add(Pair(0, j))
                    }
                }
            }
            while (queue.isNotEmpty()) {
                val size = queue.size
                for (i in 0 until size) {
                    val item = queue.poll()
                    if (type == 0) {
                        if (item.second == n - 1) {
                            return true
                        }
                    } else {
                        if (item.first == n - 1) {
                            return true
                        }
                    }
                    dir6.forEach {
                        val next = Pair(item.first + it[0], item.second + it[1])
                        if (next.first in 0 until n &&
                            next.second in 0 until n &&
                            next !in seen &&
                            matrix[next.first][next.second] == target
                        ) {
                            queue.offer(next)
                            seen.add(next)
                        }
                    }
                }
            }
            return false
        }

        val blueWin = bfs(0)
        if (blueWin) {
            var checkBlueWin = true
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (matrix[i][j] == 'B') {
                        matrix[i][j] = '.'
                        if (!bfs(0)) {
                            checkBlueWin = false
                        }
                        matrix[i][j] = 'B'
                    }
                }
            }
            if (checkBlueWin) {
                println("Case #${it + 1}: Impossible")
                return@repeat
            }
        }

        val redWin = bfs(1)
        if (redWin) {
            var checkRedWin = true
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (matrix[i][j] == 'R') {
                        matrix[i][j] = '.'
                        if (!bfs(1)) {
                            checkRedWin = false
                        }
                        matrix[i][j] = 'R'
                    }
                }
            }
            if (checkRedWin) {
                println("Case #${it + 1}: Impossible")
                return@repeat
            }
        }

        if (blueWin && b < r) {
            println("Case #${it + 1}: Impossible")
            return@repeat
        }
        if (redWin && r < b) {
            println("Case #${it + 1}: Impossible")
            return@repeat
        }

        if (blueWin && redWin) {
            println("Case #${it + 1}: Impossible")
        } else if (blueWin) {
            println("Case #${it + 1}: Blue wins")
        } else if (redWin) {
            println("Case #${it + 1}: Red wins")
        } else {
            println("Case #${it + 1}: Nobody wins")
        }
    }
}