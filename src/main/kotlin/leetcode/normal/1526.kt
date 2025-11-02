package leetcode.normal

class Solution1526 {
    fun minNumberOperations(target: IntArray): Int {
        var ans = target[0]
        for (i in 1 until target.size) {
            if (target[i] > target[i - 1]) {
                ans += target[i] - target[i - 1]
            }
        }
        return ans
    }
}