package leetcode.contest.b106

class SolutionD {
    fun goodSubsetofBinaryMatrix(grid: Array<IntArray>): List<Int> {
        if (grid.any { it.all { it == 0 } }) {
            return listOf(grid.indexOfFirst { it.all { it == 0 } })
        }

        for (i in grid.indices) {
            for (j in i + 1 until grid.size) {
                val a = grid[i]
                val b = grid[j]
                if (a.indices.all { i -> a[i] + b[i] <= 1 }) {
                    return listOf(i, j)
                }
            }
        }
        return emptyList()
    }
}