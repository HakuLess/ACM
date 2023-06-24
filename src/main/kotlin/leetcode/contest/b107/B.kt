package leetcode.contest.b107

import utils.print

fun main() {
    val s = SolutionB()
    s.longestString(2, 5, 1).print()
    s.longestString(3, 2, 2).print()
    s.longestString(2, 2, 2).print()
    // 34
    s.longestString(1, 39, 14).print()
}

class SolutionB {
    fun longestString(x: Int, y: Int, z: Int): Int {
        var aa = x
        var bb = y
        val ab = z
        if (y >= x + 1) {
            bb = x + 1
        }
        if (x >= y + 1) {
            aa = y + 1
        }
        return aa * 2 + bb * 2 + ab * 2
    }
}