package leetcode.normal

import utils.print

fun main() {
    val s = Solution650()
    s.minSteps(3).print()
}

class Solution650 {
    fun minSteps(n: Int): Int {
        var ans = 0
        var i = 2
        var cur = n
        while (i * i <= cur) {
            while (cur % i == 0) {
                ans += i
                cur /= i
            }
            i++
        }
        if (cur != 1) ans += cur
        return ans
    }
}