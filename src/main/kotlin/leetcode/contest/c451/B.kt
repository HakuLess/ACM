package leetcode.contest.c451

import utils.print
import java.util.*
import kotlin.math.abs

fun main() {
    val s = SolutionB()
    s.resultingString("zadb").print()
}

class SolutionB {
    fun resultingString(s: String): String {
        val stack = Stack<Int>()
        for (c in s) {
            val cur = c - 'a'
            if (stack.isNotEmpty()) {
                val peek = stack.peek()
                if (abs(cur - peek) == 1 || abs(cur - peek) == 25) {
                    stack.pop()
                } else {
                    stack.push(cur)
                }
            } else {
                stack.push(cur)
            }
        }
        return stack.map { 'a' + it }.joinToString("")
    }
}