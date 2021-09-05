package leetcode.contest.c257

import utils.UFS
import utils.print

fun main() {
    val s = Solution5866()
//    s.gcdSort(intArrayOf(7, 21, 3)).print()
//    s.gcdSort(intArrayOf(10, 3, 9, 6, 8)).print()
    s.gcdSort(intArrayOf(9, 9, 3, 10, 2)).print()
}

class Solution5866 {
    fun gcdSort(nums: IntArray): Boolean {
        val st = nums.sorted()
        val max = nums.maxOrNull()!!
//        val max = nums.max()!!
        val ufs = UFS(max + 1)
        val meet = BooleanArray(max + 1)
        st.forEach {
            meet[it] = true
        }
        for (i in 2..max) {
            var j = i
            while (j <= max) {
                if (meet[j])
                    ufs.union(i, j)
                j += i
            }
        }
        for (i in st.indices) {
            if (nums[i] != st[i]) {
                if (ufs.find(nums[i]) != ufs.find(st[i])) return false
            }
        }
        return true
    }
}