package leetcode.normal

class Solution1931 {
    fun colorTheGrid(m: Int, n: Int): Int {
        val mod = 1_000_000_007L

        val colors = listOf(0, 1, 2)
        val stateList = mutableListOf<List<Int>>()

        // 生成所有合法列（上下不相同）
        fun dfs(pos: Int, prev: Int, state: MutableList<Int>) {
            if (pos == m) {
                stateList.add(state.toList())
                return
            }
            for (c in colors) {
                if (c != prev) {
                    state.add(c)
                    dfs(pos + 1, c, state)
                    state.removeLast()
                }
            }
        }

        dfs(0, -1, mutableListOf())

        // 映射状态到 id，加快查找
        val idMap = HashMap<List<Int>, Int>()
        for ((i, state) in stateList.withIndex()) {
            idMap[state] = i
        }

        // 预处理：哪些状态之间可以相邻
        val canFollow = Array(stateList.size) { mutableListOf<Int>() }
        for (i in stateList.indices) {
            for (j in stateList.indices) {
                var ok = true
                for (k in 0 until m) {
                    if (stateList[i][k] == stateList[j][k]) {
                        ok = false
                        break
                    }
                }
                if (ok) canFollow[i].add(j)
            }
        }

        // dp[col][stateId] 表示前 col 列，以 stateId 为最后一列的方案数
        var dp = LongArray(stateList.size) { 1 }

        for (col in 1 until n) {
            val nextDp = LongArray(stateList.size)
            for (i in stateList.indices) {
                for (j in canFollow[i]) {
                    nextDp[i] = (nextDp[i] + dp[j]) % mod
                }
            }
            dp = nextDp
        }

        return dp.sum().mod(mod).toInt()
    }
}