package utils

/**
 * 链表操作相关工具类
 * */

//  Definition for singly-linked list.
class ListNode(var `val`: Int = 0) {
    var next: ListNode? = null
}

fun ListNode?.toIntArray(): IntArray {
    if (this == null) return intArrayOf()
    var cur = this
    val ans = arrayListOf<Int>()
    while (cur != null) {
        ans.add(cur.`val`)
        cur = cur.next
    }
    return ans.toIntArray()
}

fun IntArray.toListNode(): ListNode {
    var node = ListNode(this[0])
    val head = node
    for (i in 1 until this.size) {
        val cur = ListNode(this[i])
        node.next = cur
        node = cur
    }
    return head
}

fun ListNode?.print() {
    var node = this
    while (node != null) {
        print("${node.`val`}, ")
        node = node.next
    }
    println()
}

fun ListNode?.count(): Int {
    var c = 0
    var p: ListNode? = this ?: return 0
    while (p != null) {
        p = p.next
        c++
    }
    return c
}