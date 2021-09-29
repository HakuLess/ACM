package leetcode.normal

import utils.print

fun main() {
    val s = Solution241()
    s.diffWaysToCompute("2-1-1").joinToString().print()
    s.diffWaysToCompute("2*3-4*5").joinToString().print()
}

class Solution241 {
    fun diffWaysToCompute(expression: String): List<Int> {
        if (expression.all { it in '0'..'9' })
            return listOf(expression.toInt())

        val ans = arrayListOf<Int>()
        for (i in expression.indices) {
            val it = expression[i]
            if (it in arrayOf('+', '-', '*')) {
                val l = diffWaysToCompute(expression.substring(0, i))
                val r = diffWaysToCompute(expression.substring(i + 1))

                for (a in l)
                    for (b in r)
                        when (it) {
                            '+' -> ans.add(a + b)
                            '-' -> ans.add(a - b)
                            '*' -> ans.add(a * b)
                        }
            }
        }
        return ans
    }
}