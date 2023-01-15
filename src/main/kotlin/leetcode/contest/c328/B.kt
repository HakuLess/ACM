package leetcode.contest.c328

// todo 二维差分
class SolutionB {
    fun rangeAddQueries(n: Int, queries: Array<IntArray>): Array<IntArray> {
        val matrix = Array<IntArray>(n) { IntArray(n) }
        queries.forEach {
            for (i in it[0]..it[2]) {
                for (j in it[1]..it[3]) {
                    matrix[i][j]++
                }
            }
        }
        return matrix
    }
}