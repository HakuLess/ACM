package leetcode.contest.c400

import kotlin.collections.ArrayList

class SolutionC {
    fun clearStars(s: String): String {
        val sb = s.toCharArray()

        val arr = Array<ArrayList<Int>>(26) { ArrayList() }
        for (i in s.indices) {
            if (s[i] == '*') {
                val toRemove = arr.firstOrNull { it.isNotEmpty() }
                if (toRemove != null) {
                    val index = toRemove.removeLast()
                    sb[index] = ' '
                }
            } else {
                arr[s[i] - 'a'].add(i)
            }
        }

        return sb.filter { it != ' ' && it != '*' }.joinToString("")
    }
}