package leetcode.contest.b141

import utils.isSubSeqOf
import utils.lcs
import utils.print

fun main() {
    val s = SolutionC()
    s.maxRemovals("abbaa", "aba", intArrayOf(0, 1, 2)).print()
    s.maxRemovals("yeyeykyded", "yeyyd", intArrayOf(0, 2, 3, 4)).print()
    s.maxRemovals("bcda", "d", intArrayOf(0, 3)).print()
}

// Not Finished https://leetcode.cn/contest/biweekly-contest-141/problems/find-maximum-removals-from-source-string/
class SolutionC {
    fun maxRemovals(source: String, pattern: String, targetIndices: IntArray): Int {
        val sb = StringBuilder(source)
        var ans = 0
        for (i in targetIndices.indices.reversed()) {
            val index = targetIndices[i]
            sb.deleteCharAt(index)
//            println("remove $index with ${source[index]}  now is $sb")
            if (pattern.isSubSeqOf(sb.toString())) {
                ans++
            } else {
                sb.insert(index, source[index])
//                println("insert ${targetIndices[index]} with ${source[index]}  now is $sb")
            }
        }
        return ans
    }
}