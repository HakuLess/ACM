package leetcode.normal

import java.util.*

/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var prev: Node? = null
 *     var next: Node? = null
 *     var child: Node? = null
 * }
 */

class Node(var `val`: Int) {
    var prev: Node? = null
    var next: Node? = null
    var child: Node? = null
}

class Solution430 {
    fun flatten(root: Node?): Node? {
        var node: Node? = root
        var prev: Node? = null
        val stack = Stack<Node?>()
        while (node != null || stack.isNotEmpty()) {
            if (node == null) {
                node = stack.pop()
                node!!.prev = prev
                prev!!.next = node
            }
            if (node.child != null) {
                if (node.next != null) stack.push(node.next)
                node.child!!.prev = node
                node.next = node.child
                node.child = null
            }
            prev = node
            node = node.next
        }
        return root
    }
}