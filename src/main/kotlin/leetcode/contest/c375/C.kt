package leetcode.contest.c375

import utils.print
import java.util.*

fun main() {
    val s = SolutionC()
    s.countSubarrays(intArrayOf(1, 3, 2, 3, 3), 2).print()
}

class SolutionC {
    fun countSubarrays(nums: IntArray, k: Int): Long {
        val n = nums.size
        val max = nums.maxOrNull()!!
        val posList = ArrayList<Int>()
        posList.add(-1)
        for (i in 0 until n) {
            if (nums[i] == max) {
                posList.add(i)
            }
        }
        var left = 1
        var right = k
        var ans = 0L
        while (right < posList.size) {
            ans += (posList[left] - posList[left - 1]).toLong() * (n - posList[right])
            left++
            right++
        }
        return ans
    }

//    fun countSubarrays(nums: IntArray, k: Int): Long {
//        val maxItem = nums.maxOrNull()!!
//        var ans = 0L
//        var c = 0
//        var l = 0
//        for (item in nums) {
//            if (item == maxItem) {
//                c++
//            }
//            while (c >= k) {
//                if (nums[l++] == maxItem) c--
//            }
//            ans += l
//        }
//        return ans
//    }
}