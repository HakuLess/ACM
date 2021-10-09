package leetcode.lccup.b

import utils.TreeNode

class SolutionA {
    fun numColor(root: TreeNode?): Int {
        val set = HashSet<Int>()
        fun dfs(root: TreeNode?) {
            if (root == null) return
            set.add(root.`val`)
            dfs(root.left)
            dfs(root.right)
        }
        dfs(root)
        return set.size
    }
}