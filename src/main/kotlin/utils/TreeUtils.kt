package utils

import java.util.*
import kotlin.collections.ArrayList

/**
 * 树相关方法
 *
 * 普通二叉树 TreeNode
 *
 * 多叉树，且有parent信息
 **/

// 提交时，注意类名可能需要更换
// Definition for a binary tree node.
class TreeNode(var `val`: Int = 0) {
    var parent: TreeNode? = null
    var left: TreeNode? = null
    var right: TreeNode? = null
    var cnt = -1
}

class NTreeNode(var `val`: Int = 0, var index: Int = 0) {
    var parent: NTreeNode? = null
    var children: ArrayList<NTreeNode> = arrayListOf()
    var cnt = -1
}

// parent数组构造树
// -1代表该结点为根结点
fun IntArray.toNTree(): NTreeNode {

    val map = HashMap<Int, ArrayList<Int>>()
    for (i in this.indices) {
        map[this[i]] = map.getOrDefault(this[i], arrayListOf())
        map[this[i]]!!.add(i)
    }

    // 根节点对应的index
    val first = this.indexOf(-1)
    val root = NTreeNode(first)
    val queue: Queue<Pair<Int, NTreeNode>> = LinkedList<Pair<Int, NTreeNode>>()
    queue.add(Pair(first, root))
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (k in 0 until size) {
            val item = queue.poll()
            val node = item.second

            map[item.first]?.forEach {
                val next = NTreeNode(it)
                next.parent = node
                node.children.add(next)
                queue.offer(Pair(it, next))
            }
        }
    }

    return root
}

// parent数组构造树
// -1代表该结点为根结点
fun IntArray.toTree(): TreeNode {

    val map = HashMap<Int, ArrayList<Int>>()
    for (i in this.indices) {
        map[this[i]] = map.getOrDefault(this[i], arrayListOf())
        map[this[i]]!!.add(i)
    }

    // 根节点对应的index
    val first = this.indexOf(-1)
    val root = TreeNode(first)
    val queue: Queue<Pair<Int, TreeNode>> = LinkedList<Pair<Int, TreeNode>>()
    queue.add(Pair(first, root))
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (k in 0 until size) {
            val item = queue.poll()
            val node = item.second

            map[item.first]?.forEach {
                val next = TreeNode(it)
                next.parent = node
                if (node.left == null) node.left = next
                else if (node.right == null) node.right = next
                queue.offer(Pair(it, next))
            }
        }
    }

    return root
}

/**
 * 获取 TreeNode 的深度
 *
 * @return 自己为第1层，儿子2、孙子3，则自己的总深度是3
 * */
fun TreeNode?.depth(): Int = if (this == null) {
    0
} else {
    1 + maxOf(left.depth(), right.depth())
}

/**
 * 在 Tree 中查找值为 x 的 Node
 *
 * @param x 要查找的节点值
 * @return 值为 x 的Node，如果没有则返回null
 * */
fun TreeNode?.find(x: Int): TreeNode? = if (this == null) {
    null
} else {
    if (x == this.`val`) {
        this
    } else {
        this.left.find(x) ?: this.right.find(x)
    }
}

/**
 * 计算树的总节点数（包括自己）
 *
 * @return 总Node数
 * */
fun NTreeNode?.count(): Int = if (this == null) {
    0
} else {
    var ans = 1
    this.children.forEach {
        ans += it.count()
    }
    ans.also {
        this.cnt = it
    }
}

/**
 * 计算树的总节点数（包括自己）
 *
 * @return 总Node数
 * */
fun TreeNode?.count(): Int = if (this == null) {
    0
} else {
    (1 + this.left.count() + this.right.count()).also {
        this.cnt = it
    }
}

fun <T> Array<T>.toTree(): TreeNode? {
    if (this.isEmpty()) {
        return null
    }
    val queue: Queue<TreeNode> = LinkedList()
    var index = 0
    val root = TreeNode(this[index] as Int)
    queue.add(root)
    while (queue.isNotEmpty()) {
        val size = queue.size
        for (i in 0 until size) {
            val node = queue.poll()
            index++
            if (index <= this.lastIndex && this[index] is Int) {
                node.left = TreeNode(this[index] as Int)
                queue.offer(node.left)
            }
            index++
            if (index <= this.lastIndex && this[index] is Int) {
                node.right = TreeNode(this[index] as Int)
                queue.offer(node.right)
            }
        }
    }

    return root
}

/**
 * 判断二叉树是否为二叉搜索树
 * 严格大于小于（禁止等于）
 * */
fun TreeNode?.isBST(): Boolean {
    if (this == null) return false
    fun dfs(node: TreeNode?, up: Int, down: Int): Boolean {
        if (node == null) return true
//        println("${node.`val`} in $down..$up")
        if (node.`val` <= down || node.`val` >= up) return false
        return dfs(node.left, minOf(up, node.`val`), down) && dfs(node.right, up, maxOf(down, node.`val`))
    }
    return dfs(this.left, this.`val`, Int.MIN_VALUE) && dfs(this.right, Int.MAX_VALUE, this.`val`)
}