package leetcode.contest.c262

class Solution5894 {
    fun twoOutOfThree(nums1: IntArray, nums2: IntArray, nums3: IntArray): List<Int> {
        val set = HashSet<Int>()
        nums1.forEach {
            set.add(it)
        }
        nums2.forEach {
            set.add(it)
        }
        nums3.forEach {
            set.add(it)
        }
        val ans = arrayListOf<Int>()
        set.forEach {
            if (it in nums1 && it in nums2) {
                ans.add(it)
            } else if (it in nums1 && it in nums3) {
                ans.add(it)
            } else if (it in nums2 && it in nums3) {
                ans.add(it)
            }
        }
        return ans
    }
}