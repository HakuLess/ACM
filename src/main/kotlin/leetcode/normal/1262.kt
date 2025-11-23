package leetcode.normal

class Solution1262 {
    fun maxSumDivThree(nums: IntArray): Int {
        val dp = IntArray(3)
        dp[0] = 0
        dp[1] = Int.MIN_VALUE
        dp[2] = Int.MIN_VALUE

        for (i in 0 until nums.size) {
            val cur = dp.copyOf()
            for (j in 0..2) {
                if (dp[j] != Int.MIN_VALUE) {
                    val sum = cur[j] + nums[i]
                    val index = (sum % 3 + 3) % 3
                    dp[index] = maxOf(dp[index], sum)
                }
            }
        }
        return dp[0]
    }

//    fun maxSumDivThree(nums: IntArray): Int {
//        nums.sort()
//
//        val mod1 = ArrayList<Int>()
//        val mod2 = ArrayList<Int>()
//        for (item in nums) {
//            if (item % 3 == 1) {
//                mod1.add(item)
//            } else if (item % 3 == 2) {
//                mod2.add(item)
//            }
//        }
//        var ans = nums.sum()
//        if (ans % 3 == 1) {
//            var tmp = Int.MAX_VALUE
//            if (mod1.isNotEmpty()) {
//                tmp = minOf(tmp, mod1[0])
//            }
//            if (mod2.size >= 2) {
//                tmp = minOf(tmp, mod2[0] + mod2[1])
//            }
//            ans -= tmp
//        } else if (ans % 3 == 2) {
//            var tmp = Int.MAX_VALUE
//            if (mod2.isNotEmpty()) {
//                tmp = minOf(tmp, mod2[0])
//            }
//            if (mod1.size >= 2) {
//                tmp = minOf(tmp, mod1[0] + mod1[1])
//            }
//            ans -= tmp
//        }
//        return ans
//    }
}