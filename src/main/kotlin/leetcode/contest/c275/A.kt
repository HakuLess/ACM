package leetcode.contest.c275

class SolutionA {
    fun checkValid(matrix: Array<IntArray>): Boolean {
        val n = matrix.size
        for (i in 0 until n) {
            val a = HashSet<Int>()
            val b = HashSet<Int>()
            for (j in 0 until n) {
                a.add(matrix[i][j])
                b.add(matrix[j][i])
            }
            if (a.size != n || b.size != n) return false
        }
        return true
    }
}