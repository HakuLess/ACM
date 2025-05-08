package leetcode.normal

import utils.dir4
import java.util.*
import kotlin.collections.HashSet

class Solution3342 {
    fun minTimeToReach(moveTime: Array<IntArray>): Int {
        val seen = HashSet<Pair<Int, Int>>()
        // 时间 + 坐标
        val queue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.first })
        queue.offer(Triple(0, 0, 0))
        while (queue.isNotEmpty()) {
            val (t, x, y) = queue.poll()
            if (x == moveTime.lastIndex && y == moveTime[0].lastIndex) {
                return t
            }
            dir4.forEach {
                val nextX = x + it[0]
                val nextY = y + it[1]
                if (nextX in moveTime.indices && nextY in moveTime[0].indices && Pair(nextX, nextY) !in seen) {
                    seen.add(Pair(nextX, nextY))
                    queue.offer(Triple(maxOf(t, moveTime[nextX][nextY]) + (x + y) % 2 + 1, nextX, nextY))
                }
            }
        }

        return -1
    }
}