package leetcode.contest.c275

import utils.print

fun main() {
    val s = SolutionC()
    s.wordCount(arrayOf("ant", "act", "tack"), arrayOf("tack", "act", "acti")).print()
}

class SolutionC {
    fun wordCount(startWords: Array<String>, targetWords: Array<String>): Int {
        val arr = Array<HashSet<String>>(28) { HashSet() }
        startWords.forEach {
            val n = it.length
            arr[n].add(it.toSortedSet().joinToString(""))
        }
        var ans = 0
        targetWords.forEach {
            val n = it.length - 1
            val sorted = it.toSortedSet().joinToString("")
            if ((0..n).any {
                    val sb = sorted.substring(0, it) + sorted.substring(it + 1, n + 1)
                    sb in arr[n]
                }) ans++
        }
        return ans
    }
}