package leetcode.normal

class Solution944 {
    fun minDeletionSize(strs: Array<String>): Int {
        val n = strs.size
        val m = strs[0].length
        var ans = 0

        for (col in 0 until m) {
            for (row in 1 until n) {
                if (strs[row][col] < strs[row - 1][col]) {
                    ans++
                    break
                }
            }
        }

        return ans
    }
}