package leetcode.contest.c325

import kotlin.math.abs

class SolutionA {
    fun closetTarget(words: Array<String>, target: String, startIndex: Int): Int {
        var ans = Int.MAX_VALUE
        for (i in words.indices) {
            if (words[i] == target) {
                var diff = abs(startIndex - i)
                diff = minOf(diff, words.size - diff)
                ans = minOf(ans, diff)
            }
        }
        if (ans == Int.MAX_VALUE) return -1
        return ans
    }
}