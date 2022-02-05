package leetcode.normal

import java.util.*

class Solution1414 {
    fun findMinFibonacciNumbers(k: Int): Int {
        var a = 1
        var b = 1
        val ts = TreeSet<Int>()
        var c = a + b
        ts.add(a)
        while (c <= k) {
            ts.add(c)
            a = b
            b = c
            c = a + b
        }
        var ans = 0
        var left = k
        while (left != 0) {
            ans += left / ts.floor(left)
            left %= ts.floor(left)
        }
        return ans
    }
}