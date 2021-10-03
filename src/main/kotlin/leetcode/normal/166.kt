package leetcode.normal

import utils.print
import kotlin.math.abs

fun main() {
    val s = Solution166()
    s.fractionToDecimal(4, 333).print()
    s.fractionToDecimal(1, 5).print()
    s.fractionToDecimal(2, 1).print()
    s.fractionToDecimal(2, 3).print()
    s.fractionToDecimal(1, 6).print()
    s.fractionToDecimal(-50, 8).print()
    s.fractionToDecimal(7, -12).print()
    s.fractionToDecimal(-1, -2147483648).print()
    s.fractionToDecimal(-2147483648, 1).print()
}

class Solution166 {
    fun fractionToDecimal(numerator: Int, denominator: Int): String {
        val ans = StringBuilder()
        if (numerator.toLong() * denominator.toLong() < 0) ans.append('-')
        ans.append(abs(numerator.toLong() / denominator.toLong()))
        val seen = HashMap<Long, Boolean>()
        val res = ArrayList<Any>()
        fun dfs(x: Long, y: Long, i: Int = 0) {
            val a = x * 10 / y
            val b = (x * 10) % y
            if (x in seen.keys) {
                seen[x] = b != 0L
                res.add(')')
                return
            } else {
                res.add(a)
            }
            seen[x] = false
            if (b != 0L)
                dfs(b, y, i + 1)
            if (seen.getOrDefault(x, false)) {
                res.add(i, '(')
            }
        }

        if (numerator % denominator != 0) {
            dfs(abs(numerator.toLong() % denominator.toLong()), abs(denominator.toLong()))
            return "$ans.${res.joinToString("")}"
        }
        return ans.toString()
    }
}