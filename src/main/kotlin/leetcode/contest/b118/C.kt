package leetcode.contest.b118

class SolutionC {
    fun minimumCoins(prices: IntArray): Int {

        val seen = HashMap<Int, Int>()
        fun dfs(index: Int): Int {
            if (index in seen) return seen[index]!!
            if (index !in prices.indices) return 0
            var ans = prices[index]
            var tmp = 0
            for (i in index + 1..(index * 2 + 2)) {
                if (tmp == 0) {
                    tmp = dfs(i)
                } else {
                    tmp = minOf(tmp, dfs(i))
                }
            }
            return (ans + tmp).also {
                seen[index] = it
            }
        }
        return dfs(0)
    }
}