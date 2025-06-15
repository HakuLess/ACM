package leetcode.normal

import utils.print

fun main() {
    val s = Solution3442()
    s.maxDifference("abcabcab").print()
}

class Solution3442 {
    fun maxDifference(s: String): Int {
        var aMin = 100
        var bMax = 0
        s.groupBy { it }.forEach { t, u ->
//            println("$t : $u")
            if (u.size % 2 == 0) {
                aMin = minOf(aMin, u.size)
            } else {
                bMax = maxOf(bMax, u.size)
            }
        }

        return bMax - aMin
    }
}