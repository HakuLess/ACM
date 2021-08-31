package leetcode.normal

import utils.biFirstIndexOf
import kotlin.random.Random
import kotlin.random.nextInt

class Solution528(w: IntArray) {

    val c = IntArray(w.size)

    init {
        c[0] = w[0]
        for (i in 1 until c.size) {
            c[i] = c[i - 1] + w[i]
        }
    }

    fun pickIndex(): Int {
        val random = Random.nextInt(IntRange(1, c.last()))
        return c.biFirstIndexOf { random <= it }
    }

}

/**
 * Your Solution object will be instantiated and called as such:
 * var obj = Solution(w)
 * var param_1 = obj.pickIndex()
 */