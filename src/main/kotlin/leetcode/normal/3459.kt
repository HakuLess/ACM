package leetcode.normal

class Solution3459 {
    fun lenOfVDiagonal(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        val dirs = arrayOf(
            intArrayOf(1, 1),
            intArrayOf(1, -1),
            intArrayOf(-1, -1),
            intArrayOf(-1, 1)
        )
        val memo = Array(m) { Array(n) { Array(4) { IntArray(2) { -1 } } } }

        fun dfs(x: Int, y: Int, dir: Int, canTurn: Boolean, target: Int): Int {
            val nx = x + dirs[dir][0]
            val ny = y + dirs[dir][1]
            if (nx !in 0 until m || ny !in 0 until n || grid[nx][ny] != target) {
                return 0
            }
            val turnInt = if (canTurn) 1 else 0
            if (memo[nx][ny][dir][turnInt] != -1) {
                return memo[nx][ny][dir][turnInt]
            }
            var maxStep = dfs(nx, ny, dir, canTurn, 2 - target)
            if (canTurn) {
                maxStep = maxOf(maxStep, dfs(nx, ny, (dir + 1) % 4, false, 2 - target))
            }
            memo[nx][ny][dir][turnInt] = maxStep + 1
            return maxStep + 1
        }

        var res = 0
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) {
                    for (dir in 0 until 4) {
                        res = maxOf(res, dfs(i, j, dir, true, 2) + 1)
                    }
                }
            }
        }
        return res
    }
}