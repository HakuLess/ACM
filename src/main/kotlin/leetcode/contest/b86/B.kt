package leetcode.contest.b86

class SolutionB {
    fun isStrictlyPalindromic(n: Int): Boolean {
        return (2..n - 2).all {
            n.toString(it) == n.toString(it).reversed()
        }
    }
}