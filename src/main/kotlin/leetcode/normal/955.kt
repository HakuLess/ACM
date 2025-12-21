package leetcode.normal

class Solution955 {
    fun minDeletionSize(strs: Array<String>): Int {
        val n = strs.size
        val m = strs[0].length
        var ans = 0

        val isSorted = BooleanArray(n - 1)

        for (col in 0 until m) {
            var needDel = false
            for (i in 0 until n - 1) {
                if (!isSorted[i] && strs[i][col] > strs[i + 1][col]) {
                    needDel = true
                    break
                }
            }

            if (needDel) {
                ans++
            } else {
                for (i in 0 until n - 1) {
                    if (strs[i][col] < strs[i + 1][col]) {
                        isSorted[i] = true
                    }
                }
            }
        }

        return ans
    }
}