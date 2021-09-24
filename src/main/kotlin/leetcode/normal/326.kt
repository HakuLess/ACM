package leetcode.normal

class Solution326 {
    fun isPowerOfThree(n: Int): Boolean {
        return n > 0 && n.toString(3).let {
            it.count { it == '1' } == 1 && it.count { it == '2' } == 0
        }
    }
}