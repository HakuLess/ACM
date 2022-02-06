package leetcode.contest.c279

class SolutionA {
    fun sortEvenOdd(nums: IntArray): IntArray {
        val a = ArrayList<Int>()
        val b = ArrayList<Int>()
        for (i in nums.indices) {
            if (i % 2 == 0) {
                a.add(nums[i])
            } else {
                b.add(nums[i])
            }
        }
        a.sort()
        b.sortDescending()
        val ans = ArrayList<Int>()
        for (i in b.indices) {
            ans.add(a[i])
            ans.add(b[i])
        }
        if (a.size > b.size) {
            ans.add(a.last())
        }
        return ans.toIntArray()
    }
}