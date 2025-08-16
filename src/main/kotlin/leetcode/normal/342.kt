package leetcode.normal

class Solution342 {
    fun isPowerOfFour(n: Int): Boolean {
        return n > 0
                && n.toString(4).count { it == '1' } == 1
                && n.toString(4).count { it == '2' } == 0
                && n.toString(4).count { it == '3' } == 0
    }
}