package leetcode.contest.b115

import utils.print

fun main() {
    val s = SolutionC()
    s.getWordsInLongestSubsequence(3, arrayOf("bab", "dab", "cab"), intArrayOf(1, 2, 2)).joinToString().print()
    s.getWordsInLongestSubsequence(4, arrayOf("a", "b", "c", "d"), intArrayOf(1, 2, 3, 4)).joinToString().print()
}

class SolutionC {
    fun getWordsInLongestSubsequence(n: Int, words: Array<String>, groups: IntArray): List<String> {
        val dp = IntArray(n)
        val pre = IntArray(n) { -1 }
        for (i in words.indices) {
            for (j in i - 1 downTo 0) {
                if (groups[i] == groups[j]) continue
                if (words[j].length == words[i].length) {
                    var c = 0
                    for (k in words[j].indices) {
                        if (words[j][k] != words[i][k]) {
                            c++
                        }
                    }
                    if (c == 1) {
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1
                            pre[i] = j
                        }
                    }
                }
            }
        }
//        dp.print()
//        pre.print()

        var index = dp.indexOf(dp.maxOrNull()!!)
        val ans = ArrayList<String>()
        while (index != -1) {
            ans.add(0, words[index])
            index = pre[index]
        }

        return ans
    }
}