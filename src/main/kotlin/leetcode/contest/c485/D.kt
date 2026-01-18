package leetcode.contest.c485

import utils.print
import java.util.Stack

fun main() {
    val s = SolutionD()
    // aacb
    s.lexSmallestAfterDeletion("aaccb").print()
    s.lexSmallestAfterDeletion("ccbab").print()
    s.lexSmallestAfterDeletion("z").print()
    // a
    s.lexSmallestAfterDeletion("aa").print()
    // ab
    s.lexSmallestAfterDeletion("aba").print()
}

class SolutionD {
    fun lexSmallestAfterDeletion(s: String): String {

        var freq = IntArray(26)
        for (ch in s) freq[ch - 'a']++

        val inStack = IntArray(26)
        val stack = StringBuilder()

        for (ch in s) {
            val idx = ch - 'a'
            freq[idx]--

            while (
                stack.isNotEmpty() &&
                stack.last() > ch &&
                inStack[stack.last() - 'a'] + freq[stack.last() - 'a'] >= 2
            ) {
                val removed = stack.last() - 'a'
                stack.deleteCharAt(stack.length - 1)
                inStack[removed]--
            }

            stack.append(ch)
            inStack[idx]++
        }

        freq = IntArray(26)
        for (ch in stack) freq[ch - 'a']++

        while (stack.isNotEmpty() && freq[stack.last() - 'a'] >= 2) {
            freq[stack.last() - 'a']--
            stack.deleteCharAt(stack.length - 1)
        }

        return stack.toString()
    }
}