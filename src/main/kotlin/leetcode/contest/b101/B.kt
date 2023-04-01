package leetcode.contest.b101

import utils.print

fun main() {
    val s = SolutionB()
    s.maximumCostSubstring("adaa", "d", intArrayOf(-1000)).print()
}

class SolutionB {
    fun maximumCostSubstring(s: String, chars: String, vals: IntArray): Int {
        val v = IntArray(26)
        for (i in v.indices) v[i] = i + 1
        for (i in chars.indices) v[chars[i] - 'a'] = vals[i]

        var ans = 0
        var now = 0
        for (c in s) {
            now += v[c - 'a']
            ans = maxOf(ans, now)
            now = maxOf(now, 0)
        }
        return ans
    }
}