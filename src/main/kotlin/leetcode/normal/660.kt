package leetcode.normal

import utils.print

fun main() {
    val s = Solution660()
    s.newInteger(9).print()
}

class Solution660 {
    fun newInteger(n: Int): Int {
        return n.toString(9).toInt()
    }
}