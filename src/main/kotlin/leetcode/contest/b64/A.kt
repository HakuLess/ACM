package leetcode.contest.b64

class Solution2053 {
    fun kthDistinct(arr: Array<String>, k: Int): String {
        var cur = 0
        for (i in arr.indices) {
            if (arr.count { it == arr[i] } == 1) {
                cur++
            }
            if (cur == k) {
                return arr[i]
            }
        }
        return ""
    }
}