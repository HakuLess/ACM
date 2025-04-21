package leetcode.normal

class Solution42 {
    fun trap(height: IntArray): Int {
        val n = height.size
        val left = IntArray(n)
        val right = IntArray(n)

        var cur = 0
        for (i in height.indices) {
            cur = maxOf(cur, height[i])
            left[i] = cur
        }

        cur = 0
        for (i in height.indices.reversed()) {
            cur = maxOf(cur, height[i])
            right[i] = cur
        }

        var ans = 0
        for (i in height.indices) {
            val total = minOf(left[i], right[i])
            ans += total - height[i]
        }
        return ans
    }
}