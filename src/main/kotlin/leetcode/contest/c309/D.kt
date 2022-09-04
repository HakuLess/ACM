package leetcode.contest.c309

import utils.print
import utils.toGrid
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val s = SolutionD()
//    s.mostBooked(3, "[[3,7],[12,19],[16,17],[1,17],[5,6]]".toGrid()).print()

    // 0
//    s.mostBooked(4, "[[18,19],[3,12],[17,19],[2,13],[7,10]]".toGrid()).print()

    // 0
//    s.mostBooked(3, "[[39,49],[28,39],[9,29],[10,36],[22,47],[2,3],[4,49],[46,50],[45,50],[17,33]]".toGrid()).print()

    // 1
    s.mostBooked(2, "[[43,44],[34,36],[11,47],[1,8],[30,33],[45,48],[23,41],[29,30]]".toGrid()).print()
}

class SolutionD {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        // 开始时间排序
        meetings.sortBy { it[0] }

        // 进行中会议
        val cur = PriorityQueue<Pair<Int, Long>>(compareBy({ it.second }, { it.first }))

        // 空闲会议室
        val room = PriorityQueue<Int>(compareBy { it })
        for (i in 0 until n) {
            room.add(i)
        }

        val ans = HashMap<Int, Int>()
        var curTime = 0L
        meetings.forEach {
            curTime = maxOf(it[0].toLong(), curTime)
            while (cur.isNotEmpty() && cur.peek().second <= curTime) {
                val item = cur.poll()
                room.add(item.first)
            }
            if (room.isNotEmpty()) {
                // 占用会议室
                val c = room.poll()
                // 结束时间
                cur.offer(Pair(c, curTime + it[1] - it[0]))
                ans[c] = ans.getOrDefault(c, 0) + 1
            } else {
                // 释放会议室
                val release = cur.poll()
                if (release.second > it[0]) {
                    // 增加延期时间
                    curTime = release.second
                }
                val c = release.first
                cur.offer(Pair(c, curTime + it[1] - it[0]))
                ans[c] = ans.getOrDefault(c, 0) + 1
            }
        }

        var res = -1
        var tmp = 0
        for (i in 0 until n) {
            if (ans.getOrDefault(i, 0) > tmp) {
                tmp = ans[i]!!
                res = i
            }
        }
        return res
    }
}