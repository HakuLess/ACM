package leetcode.contest.c306

import utils.bitOne
import utils.changeBit
import utils.print

fun main() {
    val s = SolutionD()
    s.countSpecialNumbers(20).print()
    s.countSpecialNumbers(5).print()
    s.countSpecialNumbers(135).print()
}

class SolutionD {
    fun countSpecialNumbers(n: Int): Int {

        val target: IntArray = n.toString().padStart(10, '0').map { it - '0' }.reversed().toIntArray()

        val seen = HashMap<String, Int>()

        fun dfs(x: Int, limit: Boolean, state: Int): Int {
            if (x < 0) {
                return 1
            }
            val key = "$x,$limit,${state}"
            if (key in seen) return seen[key]!!
            var ans = 0
            // 顶到上限，则限制为target[x] 否则为9
            for (i in 0..(if (limit) target[x] else 9)) {
                if (state == 0 && i == 0) {
                    // 前置0，不算使用
                    ans += dfs(
                        x - 1,
                        limit && i == target[x],
                        state
                    )
                } else if (!state.bitOne(i)) {
                    // 数字i 没有被用
                    ans += dfs(
                        x - 1,
                        limit and (i == target[x]),
                        state.changeBit(i)
                    )
                }
            }
            return ans.also {
                seen[key] = it
            }
        }

        return dfs(9, true, 0) - 1
    }
}