package leetcode.normal

import utils.evalDouble
import utils.gcd
import utils.print
import kotlin.math.abs
import kotlin.math.roundToLong

fun main() {
    val s = Solution592()
//    s.fractionAddition("-1/2+1/2").print()
//    s.fractionAddition("-1/2+1/2+1/3").print()
//    s.fractionAddition("1/3-1/2").print()
//    s.fractionAddition("5/3+1/3").print()
    s.fractionAddition("5/3-3/1-6/7").print()
}

class Solution592 {
    fun fractionAddition(expression: String): String {
        var a = if (expression.startsWith("-")) {
            evalDouble("0$expression")
        } else {
            evalDouble(expression)
        }
        val b = 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10
        a *= b
        val gcd = gcd(abs(a.roundToLong()), b.toLong())
        return "${(a.roundToLong() / gcd).toInt()}/${(b / gcd).toInt()}"
    }
}