package leetcode.contest.c376

class SolutionA {
    fun findMissingAndRepeatedValues(grid: Array<IntArray>): IntArray {
        val set = HashSet<Int>()
        var a = -1
        var b = -1
        val n = grid.size
        val m = grid[0].size
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                val item = grid[i][j]
                if (item in set) {
                    a = item
                }
                set.add(item)
            }
        }
        for (i in 1..n * m) {
            if (i !in set) {
                b = i
                break
            }
        }
        return intArrayOf(a, b)
    }
}