package leetcode.contest.b160

class SolutionA {
    fun concatHex36(n: Int): String {
        return "${(n * n).toString(16)}${(n * n * n).toString(36)}".uppercase()
    }
}