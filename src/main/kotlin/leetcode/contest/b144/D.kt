package leetcode.contest.b144

class SolutionD {

    fun maxCollectedFruits(fruits: Array<IntArray>): Int {

        val movess = arrayOf(
            arrayOf(intArrayOf(1, -1), intArrayOf(1, 0), intArrayOf(1, 1)),
            arrayOf(intArrayOf(-1, 1), intArrayOf(0, 1), intArrayOf(1, 1))
        )

        val n = fruits.size
        var res = 0
        for (i in 0 until n) {
            res += fruits[i][i]
            fruits[i][i] = 0
        }

        fun dfs(
            fruits: Array<IntArray>,
            x: Int,
            y: Int,
            dp: Array<Array<Int?>>,
            countDown: Int,
            moves: Array<IntArray>
        ): Int {
            if (countDown == 0) {
                // 符合要求，返回0
                return if (x == fruits.size - 1 && y == fruits.size - 1) {
                    0
                } else -1
                // 失败，返回-1
            }
            if (dp[x][y] == null) {
                var res = -1
                for (mv in moves) {
                    val xt = x + mv[0]
                    val yt = y + mv[1]
                    if (xt >= 0 && xt < fruits.size && yt >= 0 && yt < fruits.size) {
                        val next = dfs(fruits, xt, yt, dp, countDown - 1, moves)
                        if (next != -1) {
                            // 当前策略可行，计算最大结果。
                            res = maxOf(res, next + fruits[x][y])
                        }
                    }
                }
                dp[x][y] = res
            }
            return dp[x][y]!!
        }

        fun getMax(fruits: Array<IntArray>, x: Int, y: Int, moves: Array<IntArray>): Int {
            val n = fruits.size
            val dp = Array(n) { arrayOfNulls<Int>(n) }
            return dfs(fruits, x, y, dp, n - 1, moves)
        }

        res += getMax(fruits, 0, n - 1, movess[0])
        res += getMax(fruits, n - 1, 0, movess[1])
        return res
    }
}