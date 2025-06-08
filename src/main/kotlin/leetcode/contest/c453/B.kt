package leetcode.contest.c453

class SolutionB {
    fun countPermutations(complexity: IntArray): Int {
        val mod = 1_000_000_007L

        val first = complexity[0]
        // 后续任意全排列
        if (complexity.all { it >= first } && complexity.count { it == first } == 1) {
            val n = complexity.size - 1
            var ans = 1L
            for (i in 2..n) {
                ans *= i
                ans %= mod
            }
            return ans.toInt()
        } else {
            return 0
        }
    }
}