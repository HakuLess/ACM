package leetcode.contest.c457

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val s = SolutionD()
    s.minMoves(1, 2, 5, 4).print()
    // 3
    s.minMoves(0, 1, 2, 3).print()
    s.minMoves(1, 1, 2, 2).print()
    // 20
    s.minMoves(5, 1, 350824, 287008).print()
}

class SolutionD {
    fun minMoves(sx: Int, sy: Int, tx: Int, ty: Int): Int {

        val queue: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>()
        val seen = HashSet<Pair<Int, Int>>()
        queue.offer(Pair(tx, ty))
        seen.add(Pair(tx, ty))
        var step = -1
        while (queue.isNotEmpty()) {
            val size = queue.size
            step++
            for (i in 0 until size) {
                val (x, y) = queue.poll()
                if (x == sx && y == sy) return step

                // 本质两种操作，大翻倍 or 小 + 大
                val l = ArrayList<Pair<Int, Int>>()

                // 上次操作是 x + x & max(x, y) == x
                if (x % 2 == 0 && x / 2 >= y) {
                    val nextX = x / 2
                    l.add(Pair(nextX, y))
                }

                // 上次操作是 x + x & max(x, y) == x
                if (y % 2 == 0 && y / 2 >= x) {
                    val nextY = y / 2
                    l.add(Pair(x, nextY))
                }

                // 上次操作是 小 + 大
                if (x > y && x < 2 * y) {
                    l.add(Pair(x - y, y))
                } else if (y > x && y < 2 * x) {
                    l.add(Pair(x, y - x))
                } else if (x == y) {
                    l.add(Pair(0, y))
                    l.add(Pair(x, 0))
                }

                l.forEach {
                    if (it !in seen && it.first >= sx && it.second >= sy) {
                        queue.offer(it)
                        seen.add(it)

//                        println("enter $it")
                    }
                }
            }
        }

        return -1
    }
}