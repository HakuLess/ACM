package leetcode.contest.b151

class SolutionB {
    fun countArrays(original: IntArray, bounds: Array<IntArray>): Int {
        val n = original.size
        var low = bounds[0][0]
        var high = bounds[0][1]

        for (i in 1 until n) {
            val diff = original[i] - original[i - 1]
            low = maxOf(bounds[i][0], low + diff)
            high = minOf(bounds[i][1], high + diff)

            if (low > high) return 0
        }

        return (high - low + 1)
    }
}