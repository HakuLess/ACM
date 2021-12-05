package leetcode.normal

import utils.quickPower
import utils.toBigInteger

class Solution372 {
    fun superPow(a: Int, b: IntArray): Int {
        return quickPower(a.toBigInteger(), b.joinToString("").toBigInteger(), 1337).toInt()
    }
}