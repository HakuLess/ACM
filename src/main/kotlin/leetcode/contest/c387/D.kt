package leetcode.contest.c387

import utils.SortedList

class SolutionD {
    fun resultArray(nums: IntArray): IntArray {

        val arr1 = ArrayList<Int>()
        val sortedArr1 = SortedList<Int>()
        val arr2 = ArrayList<Int>()
        val sortedArr2 = SortedList<Int>()

        arr1.add(nums[0])
        sortedArr1.insert(nums[0])

        arr2.add(nums[1])
        sortedArr2.insert(nums[1])

        for (i in 2 until nums.size) {
            val item = nums[i]
            val larger1 = sortedArr1.largerThanAndEqual(item + 1)
            val larger2 = sortedArr2.largerThanAndEqual(item + 1)

            if (larger1 > larger2) {
                arr1.add(item)
                sortedArr1.insert(item)
            } else if (larger1 < larger2) {
                arr2.add(item)
                sortedArr2.insert(item)
            } else {
                if (arr1.size <= arr2.size) {
                    arr1.add(item)
                    sortedArr1.insert(item)
                } else {
                    arr2.add(item)
                    sortedArr2.insert(item)
                }
            }
        }

        val ans = ArrayList<Int>()
        ans.addAll(arr1)
        ans.addAll(arr2)
        return ans.toIntArray()
    }
}