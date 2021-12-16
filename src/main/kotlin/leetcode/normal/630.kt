package leetcode.normal

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = Solution630()
    s.scheduleCourse("[[7,17],[3,12],[10,20],[9,10],[5,20],[10,19],[4,18]]".toGrid()).print()
}

class Solution630 {
    fun scheduleCourse(courses: Array<IntArray>): Int {
        courses.sortBy { it[1] }
        val pq = PriorityQueue<Int>(compareBy { -it })
        var cur = 0
        courses.forEach {
            if (cur + it[0] <= it[1]) {
                cur += it[0]
                pq.offer(it[0])
            } else if (pq.isNotEmpty() && pq.peek() > it[0]) {
                cur -= pq.poll()
                cur += it[0]
                pq.offer(it[0])
            }
        }
        return pq.size
    }
}