package leetcode.contest.c327

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionD()
    s.findCrossingTime(1, 3, "[[1,1,2,1],[1,1,3,1],[1,1,4,1]]".toGrid()).print()
    s.findCrossingTime(3, 2, "[[1,9,1,8],[10,10,10,10]]".toGrid()).print()
}

class SolutionD {
    fun findCrossingTime(n: Int, k: Int, time: Array<IntArray>): Int {
        // 编号 当前时间 效率
        val pq0 = PriorityQueue<Triple<Int, Int, IntArray>>(compareBy({ -(it.third[0] + it.third[2]) }, { -it.first }))
        // 左边工人
        val pql = PriorityQueue<Triple<Int, Int, IntArray>>(
            compareBy(
                { it.second },
                { -(it.third[0] + it.third[2]) },
                { -it.first })
        )
        // 左边等待过桥
        val pql1 = PriorityQueue<Triple<Int, Int, IntArray>>(compareBy({ -(it.third[0] + it.third[2]) }, { -it.first }))

        time.forEachIndexed { index, ints ->
            pq0.offer(Triple(index, 0, ints))
        }

        var left = n
        for (i in 0 until minOf(n, k)) {
            println("初始装入 ${pq0.peek()}")
            pql.offer(pq0.poll())
            left--
        }

        // 过河时间
        var cur = 0

        // 右边工人
        val pqr = PriorityQueue<Triple<Int, Int, IntArray>>(
            compareBy(
                { it.second },
                { -(it.third[0] + it.third[2]) },
                { -it.first })
        )
        // 右边等待过桥
        val pqr1 = PriorityQueue<Triple<Int, Int, IntArray>>(compareBy({ -(it.third[0] + it.third[2]) }, { -it.first }))

        // 等待搬货工人
        val pq = PriorityQueue<Triple<Int, Int, IntArray>>(
            compareBy(
                { it.second },
                { -(it.third[0] + it.third[2]) },
                { -it.first })
        )

        while ((pql.isNotEmpty() || pqr.isNotEmpty() || pql1.isNotEmpty() || pqr1.isNotEmpty()) || left != 0) {

            while (pq.isNotEmpty() && left != 0 && cur >= pq.peek().second) {
                println("${pq.joinToString()}")
                val item = pq.poll()
                println("左侧 ${item.first} 开始搬货，剩余货物 $left")
                pql.offer(item)
                left--
            }

            // 右侧工人进入等待过桥
            while (pqr.isNotEmpty()) {
                if (pqr.peek().second <= cur) {
                    pqr1.offer(pqr.poll())
                } else {
                    break
                }
            }
            // 左侧工人进入等待过桥
            while (pql.isNotEmpty()) {
                if (pql.peek().second <= cur) {
                    pql1.offer(pql.poll())
                } else {
                    break
                }
            }

//            println("当前状态 等待中 ${pq.size}   左侧等待过桥 ${pql.size}   ${pqr.size} ${pql1.size} ${pqr1.size} $left")

            if (pql1.isEmpty() && pqr1.isEmpty() && (pql.isNotEmpty() || pqr.isNotEmpty()) || (left != 0 && pq.isNotEmpty())) {
                cur++
                println("无人等待过桥，当前时间设置为下次工人时间 $cur")
//                println("${pql1.size} ${pqr1.size} ${pql.size} ${pqr.size}")
            }

            if (pqr1.isNotEmpty()) {
                // 右边等待的先过桥
                val item = pqr1.poll()
                // 设置当前时间
                cur = maxOf(cur, item.second)
                println("右侧 ${item.first} 过桥，当前时间变为 $cur 占用桥$cur..${cur + item.third[2]}")
                cur += item.third[2]

                // 进入等待搬货
                pq.offer(Triple(item.first, cur + item.third[3], item.third))

            } else if (pql1.isNotEmpty()) {
                val item = pql1.poll()
                // 设置当前时间
                cur = maxOf(cur, item.second)
                cur += item.third[0]
                println("左侧 ${item.first} 过桥，当前时间变为 $cur")
                pqr.offer(Triple(item.first, cur + item.third[1], item.third))
            }
        }
        return cur
    }
}