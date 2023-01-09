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
        val workers = arrayOfNulls<Worker>(k)
        for (i in 0 until k) {
            val worker = Worker(
                id = i,
                l2r = time[i][0],
                r2l = time[i][2],
                pickOld = time[i][1],
                putNew = time[i][3],
                priority = (time[i][0] + time[i][2]) * 10000 + i
            )
            workers[i] = worker
        }

        val lpq = PriorityQueue<Event>(compareBy { it.time })
        val rpq = PriorityQueue<Event>(compareBy { it.time })
        val lBridgePQ = PriorityQueue<Worker>(compareBy { -it.priority })
        val rBridgePQ = PriorityQueue<Worker>(compareBy { -it.priority })
        lBridgePQ.addAll(workers)

        var goods = n
        var now = 0L
        while (goods + rBridgePQ.size + rpq.size > 0) {
            // 左侧放完东西的 等待入队
            while (!lpq.isEmpty() && lpq.peek().time <= now) {
                lBridgePQ.add(lpq.remove().worker)
            }
            // 右侧拿完东西的 等待入队
            while (!rpq.isEmpty() && rpq.peek().time <= now) {
                rBridgePQ.add(rpq.remove().worker)
            }

            if (!rBridgePQ.isEmpty()) {
                // 右侧优先使用桥
                val head = rBridgePQ.remove()
                now += head.r2l.toLong()
                lpq.add(Event(now + head.putNew, head))
            } else if (!lBridgePQ.isEmpty() && goods > 0) {
                // 左侧使用桥
                goods--
                val head = lBridgePQ.remove()
                now += head.l2r.toLong()
                rpq.add(Event(now + head.pickOld, head))
            } else {
                // 没人用桥 时间快进到下一个等待桥的时间
                var minTime = Long.MAX_VALUE
                if (!lpq.isEmpty()) {
                    minTime = minOf(minTime, lpq.peek().time)
                }
                if (!rpq.isEmpty()) {
                    minTime = minOf(minTime, rpq.peek().time)
                }
                now = maxOf(now, minTime)
            }
        }
        return now.toInt()
    }
}

data class Worker(
    var id: Int,
    var l2r: Int,
    var r2l: Int,
    var pickOld: Int,
    var putNew: Int,
    var priority: Int
)

data class Event(var time: Long, var worker: Worker)