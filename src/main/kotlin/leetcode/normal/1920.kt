package leetcode.normal

class Solution1920 {
    fun buildArray(nums: IntArray): IntArray {
        for (i in nums.indices) {
            val x = nums[i]
            if (x < 0) {
                continue
            }
            var cur = i
            while (nums[cur] != i) {
                val nxt = nums[cur]
                nums[cur] = nums[nxt].inv()
                cur = nxt
            }
            nums[cur] = x.inv()
        }

        for (i in nums.indices) {
            nums[i] = nums[i].inv()
        }
        return nums
    }


//    fun buildArray(nums: IntArray): IntArray {
//        val n = nums.size
//        val ans = IntArray(n)
//        for (i in nums.indices) {
//            ans[i] = nums[nums[i]]
//        }
//        return ans
//    }
}