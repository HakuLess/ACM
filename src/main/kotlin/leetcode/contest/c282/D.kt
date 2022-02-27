package leetcode.contest.c282

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionD()
    s.minimumFinishTime("[[2,3],[3,4]]".toGrid(), 5, 4).print()
//    s.minimumFinishTime("[[1,10],[2,2],[3,4]]".toGrid(), 6, 5).print()
}

class SolutionD {
    fun minimumFinishTime(tires: Array<IntArray>, changeTime: Int, numLaps: Int): Int {

//        fun dfs(cur: Int): Int {
//            var ans = 0
//            // 当前总cost 跑圈cost 乘数
//            val pq = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
//            for (i in tires.indices) {
//                if (i == cur) {
//                    pq.offer(Triple(tires[i][0], tires[i][0], tires[i][1]))
//                } else {
//                    pq.offer(Triple(tires[i][0] + changeTime, tires[i][0], tires[i][1]))
//                }
//            }
//            repeat(numLaps) {
//                val item = pq.poll()
//                ans += item.first
//                // 换轮胎
////                pq.offer(Triple(item))
//                // 不换轮胎
//                pq.offer(Triple(item.second * item.third, item.second * item.third, item.third))
//            }
//        }

        var ans = Int.MAX_VALUE
//        for (i in tires.indices) {
//            ans = minOf(ans, dfs(i))
//        }
        return ans
    }
}