package leetcode.contest.c382

class SolutionC {
    fun flowerGame(n: Int, m: Int): Long {
        var ans = 0L
        // 偶数数量 * 奇数数量
        ans += 1L * (n / 2) * (m / 2 + m % 2)
        ans += 1L * (m / 2) * (n / 2 + n % 2)
        return ans
    }
}