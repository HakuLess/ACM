package leetcode.contest.c314

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionA()
    s.hardestWorker(70, "[[36,3],[1,5],[12,8],[25,9],[53,11],[29,12],[52,14]]".toGrid()).print()
    s.hardestWorker(70, "[[0,3],[2,5],[0,9],[1,15]]".toGrid()).print()
}

class SolutionA {
    fun hardestWorker(n: Int, logs: Array<IntArray>): Int {
        var ans = Int.MAX_VALUE
        var cur = 0
        for (i in logs.indices) {
            val lst = if (i == 0) 0 else logs[i - 1][1]
            if (logs[i][1] - lst > cur || (logs[i][1] - lst == cur && logs[i][0] < ans)) {
                ans = logs[i][0]
                cur = logs[i][1] - lst
            }
        }
        return ans
    }
}