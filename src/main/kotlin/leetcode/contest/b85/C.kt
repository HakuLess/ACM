package leetcode.contest.b85

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    // "jcoxkb"
    s.shiftingLetters("iaozjb", "[[0,4,0],[0,2,1],[1,3,1],[0,4,1],[4,4,1],[2,3,0],[5,5,0],[3,3,0],[2,3,0],[5,5,1],[5,5,1],[5,5,0]]".toGrid()).print()
}

class SolutionC {
    fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
        val aSorted = shifts.sortedBy { it[0] }
        val bSorted = shifts.sortedBy { it[1] }
        var a = 0
        var b = 0
        var offset = 0
        val ans = StringBuilder()
        for (i in s.indices) {
            // 进入
            while (a in aSorted.indices && i >= aSorted[a][0]) {
                if (aSorted[a][2] == 0) {
                    offset--
                } else {
                    offset++
                }
                a++
            }
            while (b in bSorted.indices && i > bSorted[b][1]) {
                if (bSorted[b][2] == 0) {
                    offset++
                } else {
                    offset--
                }
                b++
            }
            ans.append('a' + (s[i] - 'a' + (offset % 26) + 26) % 26)
        }
        return ans.toString()
    }
}