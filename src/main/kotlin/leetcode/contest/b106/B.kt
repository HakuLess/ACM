package leetcode.contest.b106

import utils.print

fun main() {
    val s = SolutionB()
    s.longestSemiRepetitiveSubstring("52233").print()
}

class SolutionB {
    fun longestSemiRepetitiveSubstring(s: String): Int {
        var ans = 0
        for (i in 0..s.length - 1) {
            for (j in i + 1..s.length) {
                val sb = s.substring(i, j)
//                println(sb)
                var meet = 0
                for (a in sb.indices) {
                    if (a + 1 in sb.indices) {
                        if (sb[a + 1] == sb[a]) {
                            meet++
                        }
                    }
                }
                if (meet <= 1) {
                    ans = maxOf(ans, sb.length)
                }
            }
        }

        return ans
    }
}