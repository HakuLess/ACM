package leetcode.contest.b105

import utils.print

fun main() {
    val s = SolutionB()
    s.minExtraChar("leetscode", arrayOf("leet", "code", "leetcode")).print()
//    s.minExtraChar("sayhelloworld", arrayOf("hello", "world")).print()
    // 7
    s.minExtraChar(
        "dwmodizxvvbosxxw",
        arrayOf("ox", "lb", "diz", "gu", "v", "ksv", "o", "nuq", "r", "txhe", "e", "wmo", "cehy", "tskz", "ds", "kzbu")
    ).print()
}

class SolutionB {
    fun minExtraChar(s: String, dictionary: Array<String>): Int {
        val set = dictionary.toHashSet()
        val l = ArrayList<Pair<Int, Int>>()
        val dp = IntArray(s.length + 1)
        for (i in s.indices) {
            for (j in i + 1..s.length) {
                if (s.substring(i, j) in set) {
                    l.add(Pair(i, j))
                }
            }
        }
        l.sortedBy { it.first }.forEach {
            dp[it.second] = maxOf(dp[it.second], dp[it.first] + it.second - it.first)
            for (i in dp.indices) {
                if (i == 0) continue
                dp[i] = maxOf(dp[i], dp[i - 1])
            }
        }
        return s.length - dp.last()
    }
}