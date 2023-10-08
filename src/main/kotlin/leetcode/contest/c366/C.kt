package leetcode.contest.c366

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet

fun main() {
    val s = SolutionC()
//    s.minOperations("1100011000", "0101001010", 2).print()
//    s.minOperations("10110", "00011", 4).print()

    s.minOperations("101101", "000000", 6).print()
    s.minOperations("11001011111", "01111000110", 2).print()
    s.minOperations("0110010001101011010", "1011110101000001100", 3).print()
}

class SolutionC {
    fun minOperations(s1: String, s2: String, x: Int): Int {
        val l = ArrayList<Int>()
        for (i in s1.indices) {
            if (s1[i] != s2[i]) {
                l.add(i)
            }
        }
        if (l.size % 2 != 0) return -1
        if (l.isEmpty()) return 0
//        l.joinToString().print()

        val dp = IntArray(l.size + 1)
        for (i in l.indices) {
            if (i % 2 == 0) dp[i + 1] = dp[i]
            else dp[i + 1] = dp[i] + x
            if (i > 0)
                dp[i + 1] = minOf(dp[i + 1], dp[i - 1] + l[i] - l[i - 1])
        }
        return dp.last()
    }
}