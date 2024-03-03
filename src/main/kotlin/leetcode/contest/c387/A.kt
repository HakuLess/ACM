package leetcode.contest.c387

class SolutionA {
    fun resultArray(nums: IntArray): IntArray {
        val arr1 = arrayListOf<Int>()
        val arr2 = arrayListOf<Int>()
        arr1.add(nums[0])
        arr2.add(nums[1])
        for (i in 2 until nums.size) {
            if (arr1.last() > arr2.last()) {
                arr1.add(nums[i])
            } else {
                arr2.add(nums[i])
            }
        }
        val ans = ArrayList<Int>()
        ans.addAll(arr1)
        ans.addAll(arr2)
        return ans.toIntArray()
    }
}