package leetcode.contest.b117

import utils.print
import utils.toGrid
import java.util.*

fun main() {
    val s = SolutionD()
    s.maxSpending("[[8,5,2],[6,4,1],[9,7,3]]".toGrid()).print()
}

class SolutionD {
    fun maxSpending(values: Array<IntArray>): Long {
        val m = values.size
        val n = values[0].size
        val indexArray = IntArray(m) { n - 1 }

        val pq = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
        for (i in indexArray.indices) {
            pq.offer(Pair(values[i][indexArray[i]], i))
            indexArray[i]--
        }
        var ans = 0L
        var d = 0L
        while (pq.isNotEmpty()) {
            d++
            val (value, index) = pq.poll()
            ans += value * d
            // 添加下一商品
            if (indexArray[index] >= 0) {
                pq.offer(Pair(values[index][indexArray[index]], index))
                indexArray[index]--
            }
        }
        return ans
    }
}