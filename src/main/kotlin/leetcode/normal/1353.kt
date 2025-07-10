package leetcode.normal

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution1353()
    // 4
    s.maxEvents("[[1,4],[4,4],[2,2],[3,4],[1,1]]".toGrid()).print()
    // 5
    s.maxEvents("[[1,2],[1,2],[3,3],[1,5],[1,5]]".toGrid()).print()

}

class Solution1353 {
    fun maxEvents(events: Array<IntArray>): Int {
        events.sortBy { it[0] }
        val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        var ans = 0

        var i = 0

        // 最大日期
        val lastDay = events.maxOf { it[1] }
//        println("last day is $lastDay")

        for (day in 1..lastDay) {

            while (i in events.indices && events[i][0] == day) {
                queue.add(Pair(events[i][0], events[i][1]))
//                println("day $day offer ${events[i][1]}")
                i++
            }

            while (queue.isNotEmpty() && queue.peek().second < day) {
                val item = queue.poll()
//                println("day $day poll $item")
            }

            if (queue.isNotEmpty()) {
                val item = queue.poll()
//                println("day $day add $item")
                ans++
            }
        }

        return ans
    }
}