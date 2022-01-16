package leetcode.contest.c276

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.mostPoints("[[3,2],[4,3],[4,4],[2,5]]".toGrid()).print()
    s.mostPoints("[[1,1],[2,2],[3,3],[4,4],[5,5]]".toGrid()).print()
}

class SolutionC {
    fun mostPoints(questions: Array<IntArray>): Long {
        val max = LongArray(questions.size)
        for (i in questions.indices.reversed()) {
            if (i + questions[i][1] + 1 in questions.indices) {
                max[i] = maxOf(max[i + 1], questions[i][0] + max[i + questions[i][1] + 1])
            } else if (i == questions.lastIndex) {
                max[i] = questions[i][0].toLong()
            } else {
                max[i] = maxOf(max[i + 1], questions[i][0].toLong())
            }
        }
        return max[0]
    }
}