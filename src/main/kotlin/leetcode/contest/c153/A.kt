package leetcode.contest.c153

class SolutionA {
    fun reverseDegree(s: String): Int {
        return s.withIndex().sumOf { (i, ch) ->
            val pos = 'z' - ch + 1
            pos * (i + 1)
        }
    }
}