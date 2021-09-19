package leetcode.contest.c259

class Solution5875 {
    fun finalValueAfterOperations(operations: Array<String>): Int {
        var ans = 0
        operations.forEach {
            if ("+" in it) ans++
            else ans--
        }
        return ans
    }
}