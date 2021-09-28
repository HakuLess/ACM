package leetcode.normal

import utils.TreeNode
import utils.print
import utils.printInt
import utils.toTree

fun main() {
    val s = Solution437()
    s.pathSum(arrayOf(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1).toTree(), 8).print()
    s.pathSum(arrayOf(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1).toTree(), 22).print()
}

class Solution437 {
    fun pathSum(root: TreeNode?, targetSum: Int): Int {
        if (root == null)
            return 0
        var ans = 0
        fun dfs(root: TreeNode?, map: HashMap<Int, Int>) {
            if (root == null) return
            val next = HashMap<Int, Int>()
            map.forEach { k, v ->
                next[k + root.`val`] = v
                if (k + root.`val` == targetSum) ans += v
            }
            next[0] = next.getOrDefault(0, 0) + 1
            dfs(root.left, next)
            dfs(root.right, next)
        }
        dfs(root, hashMapOf(0 to 1))
        return ans
    }
}