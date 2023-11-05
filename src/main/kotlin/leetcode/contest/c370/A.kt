package leetcode.contest.c370

class SolutionA {
    fun findChampion(grid: Array<IntArray>): Int {
        val set = HashSet<Int>()
        val n = grid.size
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (i == j) continue
                if (grid[i][j] == 0) {
                    set.add(i)
                }
            }
        }
        for (i in 0 until n) {
            if (i !in set) return i
        }
        return -1
    }
}