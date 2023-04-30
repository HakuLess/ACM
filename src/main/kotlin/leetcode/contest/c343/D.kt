package leetcode.contest.c343

import utils.print

fun main() {
    val s = SolutionD()
    s.smallestBeautifulString("abcz", 26).print()
    s.smallestBeautifulString("dc", 4).print()
}

class SolutionD {
    fun smallestBeautifulString(s: String, k: Int): String {
        val ans = s.toCharArray()
        for (i in ans.indices.reversed()) {
            for (c in ans[i] + 1 until 'a' + k) {
//                println("$i cur is $c")
                if (i - 1 in ans.indices && ans[i - 1] == c) {
                    continue
                }
                if (i - 2 in ans.indices && ans[i - 2] == c) {
                    continue
                }
                ans[i] = c
                for (j in i + 1 until ans.size) {
                    for (ch in 'a' until 'a' + k) {
                        if (j - 1 in ans.indices && ans[j - 1] == ch) {
                            continue
                        }
                        if (j - 2 in ans.indices && ans[j - 2] == ch) {
                            continue
                        }
                        ans[j] = ch
                        break
                    }
                }
                return ans.joinToString("")
            }
        }
        return ""
    }
}