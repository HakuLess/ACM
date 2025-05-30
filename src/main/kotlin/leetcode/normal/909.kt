package leetcode.normal

import java.util.*

class Solution909 {
    fun snakesAndLadders(board: Array<IntArray>): Int {
        val n = board.size
        val visited = BooleanArray(n * n + 1)

        // 将 (r, c) 映射到编号
        fun getPosition(num: Int): Pair<Int, Int> {
            val row = (num - 1) / n
            val col = (num - 1) % n
            val r = n - 1 - row
            val c = if (row % 2 == 0) col else n - 1 - col
            return Pair(r, c)
        }

        val queue: Queue<Int> = LinkedList()
        queue.offer(1)
        visited[1] = true
        var steps = 0

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val curr = queue.poll()
                if (curr == n * n) return steps

                for (i in 1..6) {
                    val next = curr + i
                    if (next > n * n) break

                    val (r, c) = getPosition(next)
                    val dest = if (board[r][c] != -1) board[r][c] else next

                    if (!visited[dest]) {
                        visited[dest] = true
                        queue.offer(dest)
                    }
                }
            }
            steps++
        }

        return -1
    }
}