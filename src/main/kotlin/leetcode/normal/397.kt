package leetcode.normal

import utils.print
import java.util.*
import kotlin.collections.HashSet

fun main() {
    val s = Solution397()
    s.integerReplacement(8).print()
    s.integerReplacement(7).print()
    s.integerReplacement(Int.MAX_VALUE).print()
}

class Solution397 {
    fun integerReplacement(n: Int): Int {
        if (n == 1) return 0
        if (n % 2 == 0) return 1 + integerReplacement(n / 2)
        return 2 + minOf(integerReplacement(n / 2), integerReplacement(n / 2 + 1))
    }
}