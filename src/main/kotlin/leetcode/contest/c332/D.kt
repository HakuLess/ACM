package leetcode.contest.c332

import utils.print

fun main() {
    val s = SolutionD()
    // 6
    s.minimumScore("abecdebe", "eaebceae").print()
    // 1
    s.minimumScore("acdedcdbabecdbebda", "bbecddb").print()
    // 1
    s.minimumScore("dcadebdecbeaedd", "dcdadeb").print()

    // 0
    s.minimumScore("adebddaccdcabaade", "adbae").print()
}

class SolutionD {
    fun minimumScore(s: String, t: String): Int {
        val left = Array<Pair<Int, Int>>(t.length) { Pair(0, 0) }
        var index = 0
        var c = 1
        for (i in t.indices) {
            while (index in s.indices) {
                if (s[index] == t[i]) {
                    left[i] = Pair(c, index)
                    c++
                    index++
                    break
                }
                index++
            }
        }

        index = s.lastIndex
        val right = Array<Pair<Int, Int>>(t.length) { Pair(0, 0) }
        c = 1
        for (i in t.indices.reversed()) {
            while (index in s.indices) {
                if (s[index] == t[i]) {
                    right[i] = Pair(c, index)
                    c++
                    index--
                    break
                }
                index--
            }
        }

        for (i in left.indices) {
            if (i == 0) continue
            if (left[i] == Pair(0, 0))
                left[i] = left[i - 1]
        }
        for (i in right.indices) {
            if (i == right.lastIndex) continue
            if (right[i] == Pair(0, 0))
                right[i] = right[i + 1]
        }
        left.joinToString().print()
        right.joinToString().print()
        var ans = t.length

        var r = 1

        for (i in left.indices) {
            ans = minOf(ans, t.length - left[i].first)
            ans = minOf(ans, t.length - right[i].first)

            r = maxOf(r, i + 1)
            while (r in right.indices && right[r].second <= left[i].second) {
                r++
            }

            if (r in right.indices && left[i].second < right[r].second)
                ans = minOf(ans, t.length - left[i].first - right[r].first)
        }
        return ans
    }
}