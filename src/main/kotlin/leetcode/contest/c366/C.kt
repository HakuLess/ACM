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
        l.joinToString().print()

        val ans = x * (l.size / 2)
        val dp = IntArray(l.size + 1)
        for (i in l.indices) {
            dp[i + 1] = maxOf(dp[i + 1], dp[i])
            if (i + 2 in dp.indices)
                dp[i + 2] = maxOf(dp[i + 2], dp[i] + x - (l[i + 1] - l[i]))
        }

        return ans - dp.last()
    }
}