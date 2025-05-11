package leetcode.normal

class Solution1550 {
    fun threeConsecutiveOdds(arr: IntArray): Boolean {
        var cur = 0
        for (i in arr.indices) {
            if (arr[i] % 2 == 1) {
                cur++
                if (cur == 3) return true
            } else {
                cur = 0
            }
        }
        return false
    }
}