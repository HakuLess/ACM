package leetcode.contest.c256

import utils.print
import utils.toAllSubSet

fun main() {
    val s = Solution5856()
    s.minSessions(intArrayOf(2, 3, 3, 4, 4, 4, 6, 7, 8, 9, 10), 15).print()
}

class Solution5856 {
    fun minSessions(tasks: IntArray, sessionTime: Int): Int {
        val seen = HashMap<String, Int>()
        fun dfs(cur: Int, mask: Int): Int {
            val key = "$cur,$mask"
            if (key in seen) return seen[key]!!
            if (mask == (1 shl (tasks.size + 1)) - 1)
                return 0
            var ans = 0
            for (i in tasks.indices) {
                if ((1 shl i + 1) and mask == 0) {
                    ans = if (cur + tasks[i] <= sessionTime) {
                        if (ans == 0) dfs(cur + tasks[i], mask or (1 shl i + 1))
                        else
                            minOf(ans, dfs(cur + tasks[i], mask or (1 shl i + 1)))
                    } else {
                        if (ans == 0) dfs(tasks[i], mask or (1 shl i + 1)) + 1
                        else minOf(ans, dfs(tasks[i], mask or (1 shl i + 1)) + 1)
                    }
                }
            }
            return ans.also {
                seen[key] = it
            }
        }
        return dfs(0, 0) + 1
    }
}