package leetcode.contest.c298

import utils.print
import java.math.BigInteger

fun main() {
    val s = SolutionC()
//    s.longestSubsequence("1001010", 5).print()
    s.longestSubsequence("010", 1).print()
}

class SolutionC {
    fun longestSubsequence(s: String, k: Int): Int {
        val arr = ArrayList<Int>()
        val ans = StringBuilder()
        for (i in s.indices.reversed()) {
            if (s[i] == '1') {
                arr.add(s.length - i - 1)
            } else {
                ans.append('0')
            }
        }
        arr.forEach {
            if ((if (ans.isEmpty())
                    BigInteger.ZERO
                else
                    ans.toString().toBigInteger(2))
                > k.toBigInteger()
            ) {
                return ans.length - 1
            } else {
                ans.insert(ans.length - it, '1')
            }
        }
        if (ans.toString().toBigInteger(2) > k.toBigInteger()) {
            return ans.length - 1
        }
        return ans.length
    }
}