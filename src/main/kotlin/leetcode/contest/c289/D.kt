package leetcode.contest.c289

import utils.print

fun main() {
    val s = SolutionD()
    s.longestPath(intArrayOf(-1, 0, 0, 1, 1, 2), "abacbe").print()
    s.longestPath(intArrayOf(-1, 0, 0, 0), "aabc").print()
}

class SolutionD {
    fun longestPath(parent: IntArray, s: String): Int {
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in parent.indices) {
            map[parent[i]] = map.getOrDefault(parent[i], arrayListOf())
            map[parent[i]]!!.add(i)
        }

        var ans = 0
        fun dfs(cur: Int): Int {
            // 收集最长的两个分支
            var a = 0
            var b = 0
            map[cur]?.forEach {
                if (s[cur] == s[it]) {
                    dfs(it)
                } else {
                    val t = dfs(it)
                    if (t > a) {
                        b = a
                        a = t
                    } else if (t > b) {
                        b = t
                    }
                }
            }
            ans = maxOf(ans, a + b + 1)
            return a + 1
        }
        dfs(0)
        return ans
    }
}