package leetcode.contest.c442

class SolutionA {
    fun maxContainers(n: Int, w: Int, maxWeight: Int): Int {
        return minOf(n * n, maxWeight / w)
    }
}