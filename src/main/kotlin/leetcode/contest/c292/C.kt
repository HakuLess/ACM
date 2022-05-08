package leetcode.contest.c292

import utils.print

fun main() {
    val s = SolutionC()
    s.countTexts("22233").print()
    s.countTexts("222222222222222222222222222222222222").print()
    s.countTexts("444479999555588866").print()
}

class SolutionC {
    fun countTexts(pressedKeys: String): Int {
        val mod = 1000000007L
        val seen = HashMap<Int, Long>()
        fun dfs(index: Int): Long {
            if (index !in pressedKeys.indices) return 1L
            if (index in seen) return seen[index]!!
            var ans = dfs(index + 1)
            if (index + 1 in pressedKeys.indices &&
                pressedKeys[index] == pressedKeys[index + 1]) {
                ans += dfs(index + 2)
            }
            if (index + 2 in pressedKeys.indices &&
                pressedKeys[index] == pressedKeys[index + 1] &&
                pressedKeys[index] == pressedKeys[index + 2]) {
                ans += dfs(index + 3)
            }
            // 7 & 9 是4次...
            if (index + 3 in pressedKeys.indices &&
                pressedKeys[index] == pressedKeys[index + 1] &&
                pressedKeys[index] == pressedKeys[index + 2] &&
                pressedKeys[index] == pressedKeys[index + 3] &&
                pressedKeys[index] in arrayOf('7', '9')
            ) {
                ans += dfs(index + 4)
            }
            return (ans % mod).also {
                seen[index] = it
            }
        }
        return dfs(0).toInt()
    }
}