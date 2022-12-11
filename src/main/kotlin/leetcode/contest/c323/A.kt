package leetcode.contest.c323

class SolutionA {
    fun deleteGreatestValue(grid: Array<IntArray>): Int {
        val n = grid.size
        val m = grid[0].size
        var ans = 0
        for (i in 0 until n) {
            grid[i].sortDescending()
        }
        for (i in 0 until m) {
            var tmp = 0
            for (j in 0 until n) {
                tmp = maxOf(tmp, grid[j][i])
            }
            ans += tmp
        }
        return ans
    }
}