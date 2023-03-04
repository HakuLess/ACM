package leetcode.contest.b99

class SolutionB {
    fun coloredCells(n: Int): Long {
        return n.toLong() * (n - 1) * 2 + 1
    }
}