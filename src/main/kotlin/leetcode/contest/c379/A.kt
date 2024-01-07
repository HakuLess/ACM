package leetcode.contest.c379

class SolutionA {
    fun areaOfMaxDiagonal(dimensions: Array<IntArray>): Int {
        val lst = dimensions.sortedWith(compareBy({ it[0] * it[0] + it[1] * it[1] }, { it[0] * it[1] })).last()
        return lst.let {
            it[0] * it[1]
        }
    }
}