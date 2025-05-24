package leetcode.contest.b157

import utils.print
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun main() {
    val s = SolutionB()
    s.maxSubstrings("abcdeafdef").print()
    s.maxSubstrings("bcdaaaab").print()
}

class SolutionB {
    fun maxSubstrings(word: String): Int {
        val n = word.length
        val dp = IntArray(n + 1)
        val lastSeen = HashMap<Char, LinkedList<Int>>()
        for (c in 'a'..'z') {
            lastSeen[c] = LinkedList()
        }

        for (i in word.indices) {
            dp[i + 1] = dp[i]
            val c = word[i]

            // 逆序查找，提前退出
            for (j in lastSeen[c]!!) {
                if (i - j + 1 >= 4) {
                    val prev = if (j == 0) 0 else dp[j]
                    dp[i + 1] = maxOf(dp[i + 1], prev + 1)
                    break
                }
            }

            lastSeen[c]!!.addFirst(i)
        }

        return dp[n]
    }
}