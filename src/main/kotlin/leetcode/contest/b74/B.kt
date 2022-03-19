package leetcode.contest.b74

import utils.print

fun main() {
    val s = SolutionB()
    s.maximumSubsequenceCount("abdcdbc", "ac").print()
    s.maximumSubsequenceCount("aabb", "ab").print()
    s.maximumSubsequenceCount("zigfj", "ju").print()
    s.maximumSubsequenceCount("iekbksdsmuuzwxbpmcngsfkjvpzuknqguzvzik", "mp").print()
}

class SolutionB {
    fun maximumSubsequenceCount(text: String, pattern: String): Long {
        val l = IntArray(text.length + 1)
        val r = IntArray(text.length + 1)
        var cur = 0L
        for (i in text.indices) {
            l[i + 1] = l[i]
            if (text[i] == pattern[0]) {
                l[i + 1]++
            }
        }
        for (i in text.indices.reversed()) {
            r[i] = r[i + 1]
            if (text[i] == pattern[1]) {
                r[i]++
                cur += l[i]
            }
        }

        var max = 0L
        for (i in l.indices) {
            max = maxOf(max, cur + l[i])
            max = maxOf(max, cur + r[i])
        }
        return max
    }
}