package leetcode.normal

class Solution1590 {
    fun minSubarray(nums: IntArray, p: Int): Int {
        val total = nums.fold(0L) { acc, v -> acc + v }
        val need = (total % p).toInt()
        if (need == 0) return 0

        val map = HashMap<Int, Int>()
        map[0] = -1

        var prefix = 0L
        var ans = nums.size

        for (i in nums.indices) {
            prefix = (prefix + nums[i]) % p
            val curr = prefix.toInt()
            val target = (curr - need + p) % p

            if (map.containsKey(target)) {
                ans = minOf(ans, i - map[target]!!)
            }

            map[curr] = i
        }

        return if (ans == nums.size) -1 else ans
    }
}