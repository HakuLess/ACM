package leetcode.contest.c413

class SolutionA {
    fun checkTwoChessboards(coordinate1: String, coordinate2: String): Boolean {
        val column1 = coordinate1[0] - 'a'
        val column2 = coordinate2[0] - 'a'

        val row1 = coordinate1[1] - '1'
        val row2 = coordinate2[1] - '1'

        return (column1 + row1) % 2 == (column2 + row2) % 2
    }
}