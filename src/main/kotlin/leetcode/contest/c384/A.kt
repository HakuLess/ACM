package leetcode.contest.c384

class SolutionA {
    fun modifiedMatrix(matrix: Array<IntArray>): Array<IntArray> {
        val n = matrix.size
        val m = matrix[0].size
        val max = IntArray(m)
        for (i in 0 until n) {
            for (j in 0 until m) {
                max[j] = maxOf(max[j], matrix[i][j])
            }
        }
        for (i in 0 until n) {
            for (j in 0 until m) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = max[j]
                }
            }
        }
        return matrix
    }
}