package leetcode.contest.c324

class SolutionD {
    fun cycleLengthQueries(n: Int, queries: Array<IntArray>): IntArray {
        fun step(a: Int, b: Int): Int {
            var ans = 0
            var l = minOf(a, b)
            var r = maxOf(a, b)
            while (l != r) {
                if (l < r) {
                    r /= 2
                } else {
                    l /= 2
                }
                ans++
            }
            return ans + 1
        }
        return queries.map {
            step(it[0], it[1])
        }.toIntArray()
    }
}