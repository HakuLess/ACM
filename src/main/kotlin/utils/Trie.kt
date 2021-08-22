package utils

class Trie<T> {
    /**
     * @param cnt 记录当前点的值，用于Remove操作
     * */
    class TrieNode<T>(val init: T? = null, var cnt: Int = 0) {
        val value: T? = init
        val children: ArrayList<TrieNode<T>> = arrayListOf()
        var isEnd = false
    }

    var root = TrieNode<T>()

    /**
     * insert value
     * */
    fun insert(value: Array<T>) {
        fun dfs(node: TrieNode<T>, depth: Int) {
            if (depth == value.size) node.isEnd = true
            if (depth !in value.indices) return
            if (!node.children.map { it.value }.contains(value[depth])) {
                node.children.add(TrieNode<T>(value[depth]))
            }
            val next = node.children.first { it.value == value[depth] }
            dfs(next, depth + 1)
        }
        dfs(root, 0)
    }

    fun <T> search(target: Array<T>): Boolean {
        fun <T> dfs(node: TrieNode<T>, i: Int): Boolean {
            if (i in target.indices) {
                if (target[i] != node.value)
                    return false
            }
            if (i == target.lastIndex) return node.isEnd
            node.children.forEach {
                if (dfs(it, i + 1)) {
                    return true
                }
            }
            return false
        }
        return dfs(root, -1)
    }
}

fun Trie<Int>.removeInt(n: Int) {
    var temp = this.root
    for (i in 31 downTo 0) {
        val x: Int = (n shr i) and 1
        val item = temp.children.first { it.value == x }
        item.cnt--
        temp = item
    }
}

fun Trie<Int>.insertInt(n: Int) {
    var temp = this.root
    for (i in 31 downTo 0) {
        val x: Int = (n shr i) and 1
        val item = temp.children.firstOrNull { it.value == x }
        if (item == null)
            temp.children.add(Trie.TrieNode(x, 1))
        else
            item.cnt++
        temp = temp.children.first { it.value == x }
    }
}

/**
 * https://www.geeksforgeeks.org/count-pairs-having-bitwise-xor-less-than-k-from-given-array/
 * */
fun Trie<Int>.smaller(n: Int, k: Int): Int {
    var count = 0
    var node: Trie.TrieNode<Int>? = this.root
    for (i in 31 downTo 0) {
        if (node == null) {
            return count
        }
        val x: Int = (n shr i) and 1
        val y: Int = (k shr i) and 1
        if (y == 1) {
            if (node.children.firstOrNull { it.value == x } != null) {
                count += node.children.first { it.value == x }.cnt
            }
            node = node.children.firstOrNull { it.value == 1 - x }
        } else {
            node = node.children.firstOrNull { it.value == x }
        }
    }
    return count
}

/**
 * https://www.geeksforgeeks.org/maximum-possible-xor-every-element-array-another-array/
 * */
fun Trie<Int>.maxXor(key: Int): Int {
    if (root.children.isEmpty()) return -1
    var temp = root
    var cur = 0
    for (i in 31 downTo 0) {
        cur *= 2
        val curBit = (key and (1 shl i)).let { if (it > 0) 1 else 0 }
        temp = if (temp.children.firstOrNull { it.value == 1 - curBit && it.cnt != 0 } != null)
            temp.children.first { it.value == 1 - curBit && it.cnt != 0 }
        else
            temp.children.first { it.value == curBit && it.cnt != 0 }
        cur += curBit xor temp.value!!
    }
    return cur
}

fun Trie<Int>.minXor(key: Int): Int {
    if (root.children.isEmpty()) return -1
    var temp = root
    var cur = 0
    for (i in 31 downTo 0) {
        cur *= 2
        val curBit = (key and (1 shl i)).let { if (it > 0) 1 else 0 }
        temp = if (temp.children.firstOrNull { it.value == curBit && it.cnt != 0 } != null)
            temp.children.first { it.value == curBit && it.cnt != 0 }
        else
            temp.children.first { it.value == 1 - curBit && it.cnt != 0 }
        cur += curBit xor temp.value!!
    }
    return cur
}

fun Trie<Int>.printInt() {
    fun dfs(node: Trie.TrieNode<Int>, cur: Int) {
        if (node.children.isEmpty()) {
            println(cur)
            return
        }
        node.children.forEach {
            dfs(it, cur * 2 + (it.value ?: 0))
        }
    }
    dfs(this.root, 0)
}

fun <T> Trie<T>.print() {
    fun dfs(node: Trie.TrieNode<T>, cur: ArrayList<T>) {
        if (node.isEnd) println(cur.joinToString(""))
        node.value?.let {
            cur.add(node.value)
        }
        if (node.children.isEmpty()) {
            println(cur.joinToString(""))
            return
        }
        node.children.forEach {
            dfs(it, cur.clone() as ArrayList<T>)
        }
    }
    dfs(this.root, arrayListOf())
}