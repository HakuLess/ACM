package leetcode.contest.c355

import utils.NTreeNode
import utils.print
import utils.toNTree

fun main() {
    val s = SolutionD()
    s.countPalindromePaths(listOf(-1, 0, 0, 1, 1, 2), "acaabc").print()
    s.countPalindromePaths(listOf(-1, 0, 0, 0, 0), "aaaaa").print()
}

class SolutionD {
    fun countPalindromePaths(parent: List<Int>, s: String): Long {
        var ans = 0L

        val tree = parent.toIntArray().toNTree()

        // 对应二进制Code 对应出现次数
        val map = HashMap<Long, Long>()
        map[0L] = 1L

        fun dfs(root: NTreeNode, cur: Long) {
            root.children.forEach {
                val next = cur xor (1L shl (s[it.`val`] - 'a'))
                for (i in 0 until 26) {
                    ans += map.getOrDefault(next xor (1L shl i), 0L)
                }
                ans += map.getOrDefault(next, 0L)
                map[next] = map.getOrDefault(next, 0L) + 1
                dfs(it, next)
            }
        }

        dfs(tree, 0L)

        return ans
    }
}