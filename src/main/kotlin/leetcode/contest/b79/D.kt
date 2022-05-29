package leetcode.contest.b79

import utils.SegmentTree
import utils.print

fun main() {
//    val b = BookMyShow(3, 999999999)
//    b.scatter(1000000000, 2).print()
//    b.gather(999999999, 2).print()
//    b.gather(999999999, 2).print()
//    b.gather(999999999, 2).print()

//    val b = BookMyShow(2, 6)
//    b.scatter(2, 1).print()
//    b.scatter(8, 0).print()

    val b = BookMyShow(2, 5)
    b.gather(4, 0).print()
    b.gather(2, 0).print()
    b.scatter(5, 1).print()
    b.scatter(5, 1).print()
}

class BookMyShow(val n: Int, val m: Int) {

    val rootMax = SegmentTree<Long>(start = 0, end = n - 1, value = 0L) { a, b ->
        maxOf(a, b)
    }

    private val state = IntArray(n) { m }

    init {
        for (i in 0 until n) {
            rootMax.update(rootMax, i, m.toLong())
        }
    }

    fun gather(k: Int, maxRow: Int): IntArray {
        // 最大值也不足够
        if (rootMax.query(rootMax, 0, maxRow) < k) {
            return intArrayOf()
        }
        val index = dfsGather(k, 0, maxRow)
        val cur = rootMax.query(rootMax, index, index)
        rootMax.update(rootMax, index, cur - k)
        state[index] = (cur - k).toInt()
        return intArrayOf(index, (m - cur).toInt())
    }

    fun dfsGather(k: Int, left: Int, right: Int): Int {
        val mid = (left + right) / 2

        if (rootMax.query(rootMax, left, mid) >= k) {
            if (left == mid) return left
            return dfsGather(k, left, mid)
        } else {
            if (right == mid + 1) return right
            return dfsGather(k, mid + 1, right)
        }
    }

    fun scatter(k: Int, maxRow: Int): Boolean {
        var canUse = 0L
        for (i in 0..maxRow) canUse += state[i]
        if (canUse < k) return false
        var left = k
        for (i in 0..maxRow) {
            if (state[i] <= left) {
                left -= state[i]
                state[i] = 0
                rootMax.update(rootMax, i, state[i].toLong())
            } else {
                state[i] -= left
                rootMax.update(rootMax, i, state[i].toLong())
                break
            }
        }
        return true
    }
}

/**
 * Your BookMyShow object will be instantiated and called as such:
 * var obj = BookMyShow(n, m)
 * var param_1 = obj.gather(k,maxRow)
 * var param_2 = obj.scatter(k,maxRow)
 */