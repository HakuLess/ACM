package utils

/**
 * 自定义链表
 * */
class LinkedNode<T>(
    var left: LinkedNode<T>? = null,
    var right: LinkedNode<T>? = null,
    var v: T? = null
) {

    fun addLeft(v: T?): LinkedNode<T> {
        val newNode = LinkedNode(v = v)
        newNode.right = this
        newNode.left = this.left
        this.left?.right = newNode
        this.left = newNode
        return newNode
    }

    fun addRight(v: T?): LinkedNode<T> {
        val newNode = LinkedNode(v = v)
        newNode.left = this
        newNode.right = this.right
        this.right?.left = newNode
        this.right = newNode
        return newNode
    }

    fun deleteLeft(): Boolean {
        if (this.left == null) return false
        this.left!!.left?.right = this
        this.left = this.left!!.left
        return true
    }

    fun deleteRight(): Boolean {
        if (this.right == null) return false
        this.right!!.right?.left = this
        this.right = this.right!!.right
        return true
    }
}

fun <T> LinkedNode<T>.moveLeft(): Boolean {
    if (this.left == null) return false
    val oPre = this.left!!
    val oNext = this.right

    this.left = oPre.left
    this.right?.left = oPre
    this.right = oPre

    oPre.right = oNext
    oPre.left?.right = this
    oPre.left = this

    return true
}

fun <T> LinkedNode<T>.moveRight(): Boolean {
    if (this.right == null) return false
    val oPre = this.left
    val oNext = this.right!!

    this.right = oNext.right
    this.left?.right = oNext
    this.left = oNext

    oNext.left = oPre
    oNext.right?.left = this
    oNext.right = this

    return true
}

fun <T> LinkedNode<T>.printTotal() {
    var cur: LinkedNode<T> = this
    while (cur.left != null) {
        cur = cur.left!!
    }
    cur.print()
}

fun <T> LinkedNode<T>.print() {
    val sb = StringBuilder()
    var cur: LinkedNode<T>? = this
    while (cur != null) {
        sb.append("${cur.v} | ")
        cur = cur.right
    }
    println(sb)
}

