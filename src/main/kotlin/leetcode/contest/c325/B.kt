package leetcode.contest.c325

import utils.print


fun main() {
    val s = SolutionB()
//    s.takeCharacters("aabaaaacaabc", 2).print()
    // 3
//    s.takeCharacters("ccbabcc", 1).print()
    s.takeCharacters("cbbac", 1).print()
}

class SolutionB {
    fun takeCharacters(s: String, k: Int): Int {
        if (k == 0) return 0
        if (s.groupBy { it }.size != 3 || s.groupBy { it }.any { it.value.size < k }) return -1
        val sb = "$s$s"
        val arr = IntArray(3)
        var ans = s.length
        var l = 0
        val n = s.length
        for (i in sb.indices) {
            arr[sb[i] - 'a']++
            while (arr.all { it >= k }) {
                if (l == 0 || i == n - 1 || n in l..i)
                    ans = minOf(ans, i - l + 1)
                arr[sb[l] - 'a']--
                l++
            }
        }
        return ans
    }
}