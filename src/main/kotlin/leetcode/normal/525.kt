package leetcode.normal

class Solution525 {
    fun findMaxLength(nums: IntArray): Int {
        var cnt = 0
        val map = HashMap<Int, Int>()
        map[0] = 0
        var ans = 0
        for (i in nums.indices) {
            if (nums[i] == 1) {
                cnt++
            } else {
                cnt--
            }
            if (cnt in map.keys) {
                ans = maxOf(ans, i - map[cnt]!!)
            } else {
                map[cnt] = i
            }
        }
        return ans
    }
}