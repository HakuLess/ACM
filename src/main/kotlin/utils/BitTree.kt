package utils

/**
 * 树状数组
 *
 * 累加线段树
 * */
class BitTree(val size: Int) {

    val tree1 = HashMap<Int, Int>()
    val tree2 = HashMap<Int, Int>()

    fun lowbit(index: Int): Int {
        return index and (-index)
    }

    fun add(left: Int, right: Int, delta: Int) {
        _add(left, delta)
        _add(right + 1, -delta)
    }


    fun query(left: Int, right: Int): Int {
        return _query(right) - _query(left - 1)
    }

    fun _add(i: Int, delta: Int) {
        var index = i
        val rawIndex = index
        while (index <= size) {
            tree1[index] = tree1.getOrDefault(index, 0) + delta
            tree2[index] = tree2.getOrDefault(index, 0) + (rawIndex - 1) * delta
            index += lowbit(index)
        }
    }

    fun _query(i: Int): Int {
        var index = i
        if (index > size)
            index = size

        val rawIndex = index
        var res = 0
        while (index > 0) {
            res += rawIndex * tree1.getOrDefault(index, 0) - tree2.getOrDefault(index, 0)
            index -= lowbit(index)
        }
        return res
    }
}