package leetcode.contest.c339

class SolutionC {
    fun miceAndCheese(reward1: IntArray, reward2: IntArray, k: Int): Int {
        val n = reward1.size
        val cheese = Array(n) { i -> Pair(reward1[i], reward2[i]) }
        cheese.sortByDescending { it.first - it.second }
        var ans = 0
        for (i in 0 until k) {
            ans += cheese[i].first
        }
        for (i in k until n) {
            ans += cheese[i].second
        }
        return ans
    }
}