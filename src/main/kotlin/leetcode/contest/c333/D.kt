package leetcode.contest.c333

import utils.lcp
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
//    s.findTheString("[[4,0,2,0],[0,3,0,1],[2,0,2,0],[0,1,0,1]]".toGrid()).print()
//    s.findTheString("[[4,3,2,1],[3,3,2,1],[2,2,2,1],[1,1,1,3]]".toGrid()).print()
}

class SolutionD {
    fun findTheString(lcp: Array<IntArray>): String {
        val n = lcp.size
        val ans = ArrayList<Char>()
        var offset = 0
        for (i in lcp.indices) {
            for (j in lcp[0].indices) {
                val item = lcp[i][j]
                if (item != 0) {
                    if (j in ans.indices) {
                        ans.add(ans[j])
                    } else {
                        ans.add('a' + offset)
                        offset++
                    }
                    break
                }
            }
        }
        val result = ans.joinToString("")
        if (result.any { it !in 'a'..'z' }) return ""

        for (i in result.indices) {
            for (j in result.indices) {
                if (result[i] == result[j]) {
                    if (i == n - 1 || j == n - 1) {
                        if (lcp[i][j] != 1) return ""
                    } else if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                        return ""
                    }
                }
                if (result[i] != result[j] && lcp[i][j] != 0) {
                    return ""
                }
            }
        }

        return result
    }
}