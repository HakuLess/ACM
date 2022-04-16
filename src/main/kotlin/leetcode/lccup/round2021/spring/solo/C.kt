package leetcode.lccup.round2021.spring.solo

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
    s.getNumber(arrayOf(1, null, 2, null, 3, null, 4, null, 5).toTree(), "[[1,2,4],[1,1,3],[0,3,5]]".toGrid()).print()
//    s.getNumber(
//        arrayOf(4, 2, 7, 1, null, 5, null, null, null, null, 6).toTree(),
//        "[[0,2,2],[1,1,5],[0,4,5],[1,5,7]]".toGrid()
//    ).print()
}

// 线段树 区间更新！！
class SolutionC {
    fun getNumber(root: TreeNode?, ops: Array<IntArray>): Int {
        val set = hashSetOf<Int>()
        fun dfs(root: TreeNode?) {
            if (root == null) return
            set.add(root.`val`)
            dfs(root.left)
            dfs(root.right)
        }
        dfs(root)

        val map = HashMap<Int, Int>()
        var c = 0
        set.sorted().forEach {
            map[it] = c++
        }

//        map.printInt()

        val root = SegmentTree<Int>(start = 0, end = c - 1, value = 0) { a, b ->
            a + b
        }

        ops.forEach {
            val make = map[it[2]]!! - map[it[1]]!! - 1
            println("make ${map[it[1]]}..${map[it[2]]} to ${if (it[0] == 0) 0 else make}")
//            println("make ${map[it[1]]}..${map[it[2]]} to 1")
            root.update(root, map[it[1]]!!, map[it[2]]!!, if (it[0] == 0) 0 else map[it[2]]!! - map[it[1]]!! - 1)
//            if (it[0] == 0) {
//                root.update(root, map[it[1]]!!, map[it[2]]!!, 0)
//            } else {
//                println("make ${map[it[1]]}..${map[it[2]]} to 1")
//                root.update(root, map[it[1]]!!, map[it[2]]!!, 1)
//            }
            root.print()
//            println("cur ${root.query(root, 0, c)}")
        }
//        println("2..4 ${root.query(root, 2, 4)}")
        for (i in 0..c - 1) {
            println("$i: ${root.query(root, i, i)}")
        }
        return root.query(root, 0, c - 1)
    }
}