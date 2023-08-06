package leetcode.contest.c357

import utils.print
import utils.toGrid
import java.util.*


fun main() {
    val s = SolutionD()
//    s.findMaximumElegance("[[1,1],[2,1],[3,1]]".toGrid(), 3).print()

    // 17
    s.findMaximumElegance("[[3,2],[5,1],[10,1]]".toGrid(), 2).print()

    // 15
//    s.findMaximumElegance("[[5,1],[6,1],[8,1]]".toGrid(), 2).print()
    // 19
//    s.findMaximumElegance("[[8,1],[10,1],[2,3]]".toGrid(), 2).print()
}

class SolutionD {
    fun findMaximumElegance(items: Array<IntArray>, k: Int): Long {
        if (items.size < k) return 0

        items.sortByDescending { it[0] }
        val map = HashMap<Int, Int>()
        val queue: ArrayDeque<IntArray> = ArrayDeque()

        var sum = 0L
        for (i in 0 until k) {
            sum += items[i][0]
            map[items[i][1]] = map.getOrDefault(items[i][1], 0) + 1
            queue.push(items[i])
        }
        var ans = sum + 1L * map.keys.size * map.keys.size

        for (i in k until items.size) {
            while (items[i][1] !in map.keys && queue.isNotEmpty()) {
                if (map[queue.peek()[1]]!! > 1) {
                    sum += items[i][0] - queue.peek()[0]
                    ans = maxOf(ans, sum + (map.keys.size + 1L) * (map.keys.size + 1))
                    map[queue.peek()[1]] = map[queue.peek()[1]]!! - 1
                    map[items[i][1]] = 1
                    queue.pop()
                    queue.push(items[i])
                    break
                }
                queue.pop()
            }
        }
        return ans
    }
}