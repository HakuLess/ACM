package leetcode.contest.b75

import utils.SuffixArray
import utils.print

fun main() {
    val s = SolutionD()
    // 9
    s.sumScores("babab").print()
    // 14
    s.sumScores("azbazbzaz").print()
    // 69
    s.sumScores("mrahgszbidrjkdhyocyixreymnjdbaonvctvymstfmjifrftgbwsafqsofqnxmuwb").print()
}

class SolutionD {
    fun sumScores(s: String): Long {
        val suf = SuffixArray(s)
        var ans = 0L
        val sufArr = suf.getSuffixArray()
        val index = sufArr.indexOfFirst { it.index == 0 }
        // 排序后，相邻子串的最长公共前缀长度
        val sa = suf.kasai()

        var min = Int.MAX_VALUE
        for (i in index - 1 downTo 0) {
            min = minOf(sa[i], min)
            if (min == 0) break
            ans += min
        }
        min = Int.MAX_VALUE
        for (i in index until s.length) {
            min = minOf(sa[i], min)
            if (min == 0) break
            ans += min
        }
        ans += s.length
        return ans
    }
}