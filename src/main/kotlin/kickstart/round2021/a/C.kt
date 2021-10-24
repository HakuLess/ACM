package kickstart.round2021.a

import utils.dir4
import java.util.*
import kotlin.collections.ArrayList

fun main() {

    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (r, c) = readLine()!!.trim().split(' ').map { it.toInt() }
        val matrix = ArrayList<IntArray>()
        repeat(r) {
            matrix.add(readLine()!!.trim().split(' ').map { it.toInt() }.toIntArray())
        }

        // 矩阵数据队列
        val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { -it.third })
        for (i in 0 until r) {
            for (j in 0 until c) {
                pq.offer(Triple(i, j, matrix[i][j]))
            }
        }

        // 处理数据队列
        val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { -it.third })
        queue.offer(pq.peek())

        var height = pq.peek().third
        val mt = Array<IntArray>(r) { IntArray(c) }

        while (queue.isNotEmpty()) {
            while (pq.isNotEmpty() && pq.peek().third == height) {
                queue.offer(pq.poll())
            }
            val size = queue.size
            for (i in 0 until size) {
                val item = queue.poll()
                if (item.first !in 0 until r || item.second !in 0 until c) continue
                if (mt[item.first][item.second] == 0) {
                    mt[item.first][item.second] = height

                    dir4.forEach {
                        queue.offer(Triple(item.first + it[0], item.second + it[1], height - 1))
                    }
                }
            }
            height--
        }

        var ans = 0L
        for (i in 0 until r) {
            for (j in 0 until c) {
                ans += mt[i][j] - matrix[i][j]
            }
        }
        println("Case #${it + 1}: ${ans}")
    }
}