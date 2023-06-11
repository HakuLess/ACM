package leetcode.contest.c349

import utils.print

fun main() {
    val s = SolutionB()
    s.smallestString("cbabc").print()
    s.smallestString("leetcode").print()
    s.smallestString("a").print()
    s.smallestString("aa").print()
    s.smallestString("baa").print()
}

class SolutionB {
    fun smallestString(s: String): String {
        var l = -1
        var r = s.length
        for (i in s.indices) {
            if (s[i] != 'a') {
                if (l == -1) {
                    l = i
                }
            } else {
                if (l != -1) {
                    r = i - 1
                    break
                }
            }
        }

        if (l == -1) {
            l = s.lastIndex
        }

        val ans = StringBuilder()
        for (i in s.indices) {
            if (i in l..r) {
                if (s[i] == 'a') {
                    ans.append('z')
                } else {
                    ans.append(s[i] - 1)
                }
            } else {
                ans.append(s[i])
            }
        }
        return ans.toString()
    }
}