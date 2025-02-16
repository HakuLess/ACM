package leetcode.contest.c437

class SolutionA {
    fun hasSpecialSubstring(s: String, k: Int): Boolean {
        if (k > s.length) return false

        var left = 0
        while (left + k <= s.length) {
            val sub = s.substring(left, left + k)

            if (sub.all { it == sub[0] }) {
                val prev = if (left > 0) s[left - 1] else null
                val next = if (left + k < s.length) s[left + k] else null

                if ((prev == null || prev != sub[0]) && (next == null || next != sub[0])) {
                    return true
                }
            }
            left++
        }
        return false
    }
}