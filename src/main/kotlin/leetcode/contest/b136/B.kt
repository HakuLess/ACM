package leetcode.contest.b136

class SolutionB {
    fun minFlips(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size

        fun minFlipsToPalindrome(arr: IntArray): Int {
            var flips = 0
            for (i in 0 until arr.size / 2) {
                if (arr[i] != arr[arr.size - 1 - i]) {
                    flips++
                }
            }
            return flips
        }

        var rowFlips = 0
        for (i in 0 until m) {
            rowFlips += minFlipsToPalindrome(grid[i])
        }

        var colFlips = 0
        for (j in 0 until n) {
            val column = IntArray(m) { grid[it][j] }
            colFlips += minFlipsToPalindrome(column)
        }

        return minOf(rowFlips, colFlips)
    }
}