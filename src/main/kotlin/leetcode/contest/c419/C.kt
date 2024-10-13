package leetcode.contest.c419

import utils.print

fun main() {
    val s = SolutionC()
    s.countWinningSequences("FFF").print()
}

class SolutionC {
    fun countWinningSequences(s: String): Int {
        val MOD = 1_000_000_007
        val n = s.length
        val creatures = arrayOf('F', 'W', 'E')  // 火龙, 水蛇, 地精
        val memo = Array(n) { Array(3) { IntArray(2001) { -1 } } }  // 记忆化数组
        val offset = 1000  // 平移得分差

        // 计算每轮出招的得分变化
        fun scoreChange(alice: Char, bob: Char): Int {
            return when (alice) {
                'F' -> if (bob == 'W') 1 else if (bob == 'E') -1 else 0
                'W' -> if (bob == 'E') 1 else if (bob == 'F') -1 else 0
                'E' -> if (bob == 'F') 1 else if (bob == 'W') -1 else 0
                else -> 0
            }
        }

        // DFS 记忆化搜索函数，返回当前状态的有效出招数
        fun dfs(index: Int, prevBob: Int, scoreDiff: Int): Int {
            if (index == n) {
                return if (scoreDiff > 0) 1 else 0  // 最后一个回合结束，检查 Bob 是否获胜
            }

            // 如果已经计算过，直接返回
            if (memo[index][prevBob][scoreDiff + offset] != -1) {
                return memo[index][prevBob][scoreDiff + offset]
            }

            var result = 0
            val alice = s[index]

            // 尝试 Bob 的每一种出招 (F, W, E)
            for (bob in 0..2) {
                if (bob != prevBob) {  // 确保 Bob 连续两轮不能出相同生物
                    val newDiff = scoreDiff + scoreChange(alice, creatures[bob])
                    result = (result + dfs(index + 1, bob, newDiff)) % MOD
                }
            }

            memo[index][prevBob][scoreDiff + offset] = result  // 记忆化当前状态
            return result
        }

        // 初始化从第 0 轮开始，Bob 还没有出招 (-1 表示未出招)
        var totalResult = 0
        for (bob in 0..2) {  // Bob 第一次出招的三种情况
            totalResult = (totalResult + dfs(1, bob, scoreChange(s[0], creatures[bob]))) % MOD
        }

        return totalResult
    }
}