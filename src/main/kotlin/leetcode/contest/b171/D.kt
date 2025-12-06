package leetcode.contest.b171

import utils.SortedList
import utils.print

fun main() {
    val s = SolutionD()
    s.minInversionCount(intArrayOf(3, 1, 2, 5, 4), 3).print()
    s.minInversionCount(intArrayOf(5, 3, 2, 1), 4).print()
    s.minInversionCount(intArrayOf(2, 1), 1).print()
}

class SolutionD {
    fun minInversionCount(nums: IntArray, k: Int): Long {
        val window = SortedList<Int>()
        var inv = 0L

        for (i in 0 until k) {
            val x = nums[i]
            val greater = window.largerThanAndEqual(x + 1)
            inv += greater
            window.insert(x)
        }

        var ans = inv

        for (i in k until nums.size) {
            val add = nums[i]
            val remove = nums[i - k]

            val smaller = window.smallerThanAndEqual(remove - 1)
            inv -= smaller
            window.remove(remove)

            val greater = window.largerThanAndEqual(add + 1)
            inv += greater
            window.insert(add)

            ans = minOf(ans, inv)
        }

        return ans
    }

//    fun minInversionCount(nums: IntArray, k: Int): Long {
//
//        val n = nums.size
//
//        val sorted = nums.distinct().sorted()
//        fun compress(x: Int) = sorted.binarySearch(x) + 1
//        val maxC = sorted.size
//
//        val fenwick = FenwickTree(maxC)
//
//        var cur = 0L
//        var ans = Long.MAX_VALUE
//
//        for (i in 0 until k) {
//            val c = compress(nums[i])
//            cur += fenwick.countGT(c, i)
//            fenwick.update(c, 1)
//        }
//
//        ans = minOf(ans, cur)
//
//        for (i in k until n) {
//            val cAdd = compress(nums[i])
//            val cRem = compress(nums[i - k])
//
//            cur -= fenwick.countLT(cRem)
//            fenwick.update(cRem, -1)
//
//            val windowSize = k - 1
//            cur += fenwick.countGT(cAdd, windowSize)
//            fenwick.update(cAdd, 1)
//
//            ans = minOf(ans, cur)
//        }
//
//        return ans
//    }
}