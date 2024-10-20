package leetcode.contest.c420

import utils.hash
import utils.isPalindrome
import utils.print

fun main() {
    val s = SolutionD()
    s.findAnswer(intArrayOf(-1, 0, 0, 1, 1, 2), "aababa").print()

}

// TODO Rolling Hash
// https://zhwebsite.com/2024/10/20/leetcode-contest-420/
class SolutionD {
    fun findAnswer(parent: IntArray, s: String): BooleanArray {
        fun buildTree(parent: IntArray): List<MutableList<Int>> {
            val tree = List(parent.size) { mutableListOf<Int>() }
            for (i in 1 until parent.size) {
                tree[parent[i]].add(i)
            }
            return tree
        }

        val seen = HashMap<Int, String>()
        val n = parent.size
        val answer = BooleanArray(n)

        fun dfs(x: Int, s: String, tree: List<List<Int>>): String {
            if (x in seen) {
                return seen[x]!!
            }
            val sb = StringBuilder()
            // 按照节点编号升序遍历所有子节点
            for (child in tree[x]) {
                sb.append(dfs(child, s, tree))
            }
            // 添加当前节点字符
            sb.append(s[x])
            seen[x] = sb.toString()
//            println("${sb.toString()} with ${sb.hashCode()}")
            if (sb.toString().isPalindrome()) {
                answer[x] = true
            }
            return sb.toString()
        }

        val tree = buildTree(parent)
        dfs(0, s, tree)

//        seen.forEach { t, u ->
//            println("$t : $u")
//        }

        return answer
    }
}