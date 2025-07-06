package leetcode.normal

class Solution3307 {
    fun kthCharacter(k: Long, operations: IntArray): Char {
        var ans = 0
        var curK = k
        while (curK != 1L) {
            var t = 63 - curK.countLeadingZeroBits()
            if ((1L shl t) == curK) {
                t--
            }
            curK -= (1L shl t)
            if (operations[t] != 0) {
                ans++
            }
        }
        return ('a' + (ans % 26))
    }
}