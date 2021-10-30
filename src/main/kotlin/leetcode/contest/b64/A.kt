package leetcode.contest.b64

class SolutionA {
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