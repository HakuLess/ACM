package leetcode.contest.c313

import utils.print

fun main() {
    val s = SolutionD()
    s.deleteString("abcabcdabc").print()
    s.deleteString("aaabaab").print()
    s.deleteString("aaaaa").print()
}

class SolutionD {
    fun deleteString(s: String): Int {
        val n = s.length
        val m = Array<BooleanArray>(n) { BooleanArray(n) }

        for (i in s.indices) {
            for (j in i until s.length) {
                if (j * 2 - i + 1 >= s.length) continue
                val left = s.substring(IntRange(i, j))
                val right = s.substring(IntRange(j + 1, j * 2 - i + 1))
                if (left == right) {
                    m[i][j] = true
                }
            }
        }

        val seen = HashMap<Int, Int>()
        fun dfs(cur: Int): Int {
            if (cur in seen) return seen[cur]!!
            var tmp = 0
            m[cur].forEachIndexed { index, it ->
                if (it) {
                    tmp = maxOf(tmp, dfs(index + 1))
                }
            }
            return (tmp + 1).also {
                seen[cur] = it
            }
        }

        return dfs(0)
    }
}