package leetcode.contest.b135

class SolutionA {
    fun losingPlayer(x: Int, y: Int): String {
        val c = minOf(x, y / 4)
        return if (c % 2 == 0) "Bob" else "Alice"
    }
}