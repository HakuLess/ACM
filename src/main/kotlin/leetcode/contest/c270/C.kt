package leetcode.contest.c270

import utils.*

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
fun main() {
    val s = SolutionC()
    s.getDirections(arrayOf(5, 1, 2, 3, null, 6, 4).toTree(), 3, 6).print()
    s.getDirections(arrayOf(2, 1).toTree(), 2, 1).print()
    s.getDirections(arrayOf(5, 8, 3, 1, null, 4, 7, 6, null, null, null, null, null, null, 2).toTree(), 4, 3).print()
}

class SolutionC {
    fun getDirections(root: TreeNode?, startValue: Int, destValue: Int): String {
        var sl = ""
        var sr = ""
        fun dfs(node: TreeNode?, sb: ArrayList<Char>) {
            if (node == null) return
            if (node.`val` == startValue) {
                sl = sb.joinToString("")
            }
            if (node.`val` == destValue) {
                sr = sb.joinToString("")
            }
            sb.add('L')
            dfs(node.left, sb)
            sb.removeAt(sb.lastIndex)
            sb.add('R')
            dfs(node.right, sb)
            sb.removeAt(sb.lastIndex)
        }
        dfs(root, ArrayList())
        var start = 0
        while (start in sl.indices && start in sr.indices) {
            if (sl[start] == sr[start]) {
                start++
                continue
            }
            break
        }

        return sl.substring(start, sl.length).map { "U" }.joinToString("") + sr.substring(start, sr.length)
    }


}