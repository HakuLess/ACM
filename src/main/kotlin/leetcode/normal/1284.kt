package leetcode.normal

import utils.changeBit
import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = Solution1284()
    s.minFlips("[[0,0],[0,1]]".toGrid()).print()
    s.minFlips("[[0],[1],[1]]".toGrid()).print()
}

class Solution1284 {
    fun minFlips(mat: Array<IntArray>): Int {
        val target = mat.joinToString("") { it.joinToString("") }.toInt(2)
        val n = mat.size
        val m = mat[0].size

        val queue: Queue<Int> = LinkedList<Int>()
        queue.offer(0)
        val seen = HashSet<Int>()
        seen.add(0)
        var step = -1
        while (queue.isNotEmpty()) {
            step++
            for (i in queue.indices) {
                val cur = queue.poll()
                if (cur == target) return step
                for (j in 0 until n * m) {
                    var next = cur.changeBit(j)
                    hashSetOf(1, -1, m, -m).forEach {
                        if (m != 1 && (it == 1 || it == -1) && ((j + it) / m != j / m)) {
                            // +1-1后若不在同一行，则无需处理
                            // 列数为1时，只需要执行+1-1
                        } else if (j + it in 0 until n * m) {
                            next = next.changeBit(j + it)
                        }
                    }
                    if (next !in seen) {
                        seen.add(next)
                        queue.offer(next)
                    }
                }
            }
        }

        return -1
    }
}