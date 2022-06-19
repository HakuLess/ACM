package leetcode.contest.c298

import utils.print
import java.math.BigInteger

fun main() {
    val s = SolutionC()
    s.longestSubsequence("1001010", 5).print()
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

        fun cal(sb: String): BigInteger {
            var ans = BigInteger.ZERO
            var step = BigInteger.ONE
            sb.reversed().forEach {
                if (it == '1') {
                    ans += step
                }
                step *= 2.toBigInteger()
            }
            return ans
        }

        arr.forEach {
            if (cal(ans.toString()) > k.toBigInteger()) {
                return ans.length - 1
            } else {
                ans.insert(ans.length - it, '1')
//                println("insert $it got $ans")
            }
        }
        return ans.length
    }
}