package leetcode.contest.c329

class SolutionB {
    fun sortTheStudents(score: Array<IntArray>, k: Int): Array<IntArray> {
        return score.sortedByDescending { it[k] }.toTypedArray()
    }
}