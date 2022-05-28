package leetcode.contest.b79

import utils.print

fun main() {
    val b = BookMyShow(3, 999999999)
    b.scatter(1000000000, 2).print()
    b.gather(999999999, 2).print()
}

class BookMyShow(val n: Int, val m: Int) {

    private val state = IntArray(n) { m }

    fun gather(k: Int, maxRow: Int): IntArray {
        for (i in 0..maxRow) {
            if (state[i] >= k) {
                state[i] -= k
                return intArrayOf(i, m - state[i] - k)
            }
        }
        return intArrayOf()
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
            } else {
                state[i] -= left
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