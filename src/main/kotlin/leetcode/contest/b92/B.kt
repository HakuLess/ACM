package leetcode.contest.b92

class SolutionB {
    fun onesMinusZeros(grid: Array<IntArray>): Array<IntArray> {
        val m = grid.size
        val n = grid[0].size

        val onesCol = IntArray(m)
        val zerosCol = IntArray(m)
        val onesRow = IntArray(n)
        val zerosRow = IntArray(n)

        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 0) {
                    zerosRow[j]++
                    zerosCol[i]++
                } else {
                    onesRow[j]++
                    onesCol[i]++
                }
            }
        }

        val ans = Array<IntArray>(m) { IntArray(n) }
        for (i in 0 until m) {
            for (j in 0 until n) {
                ans[i][j] = onesRow[j] + onesCol[i] - zerosRow[j] - zerosCol[i]
            }
        }
        return ans
    }
}