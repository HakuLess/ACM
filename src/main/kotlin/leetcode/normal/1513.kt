package leetcode.normal


class Solution1513 {
    fun numSub(s: String): Int {
        val mod = 1_000_000_007L
        var cur = 0L
        var ans = 0L

        for (c in s) {
            if (c == '1') {
                cur++
                ans = (ans + cur) % mod
            } else {
                cur = 0
            }
        }
        return ans.toInt()
    }
}
