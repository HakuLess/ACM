package leetcode.contest.b84

import utils.print

fun main() {
    val s = SolutionC()
    s.taskSchedulerII(intArrayOf(1, 2, 1, 2, 3, 1), 3).print()
    s.taskSchedulerII(intArrayOf(5, 8, 8, 5), 2).print()
}

class SolutionC {
    fun taskSchedulerII(tasks: IntArray, space: Int): Long {
        var ans = 0L
        val map = HashMap<Int, Long>()
        val diff = 1L + space
        for (i in tasks.indices) {
            val lst = map.getOrDefault(tasks[i], -diff)
            if (ans - lst < diff) {
                ans = lst + diff
            } else {
                ans++
            }
            map[tasks[i]] = ans
//            println("$i ${tasks[i]} $ans")
        }
        return ans
    }
}