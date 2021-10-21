package leetcode.contest.b63

import utils.biLastIndexOf
import utils.print

fun main() {
    val s = Solution5887()
    s.kthSmallestProduct(intArrayOf(2, 5), intArrayOf(3, 4), 2).print()
    s.kthSmallestProduct(intArrayOf(-4, -2, 0, 3), intArrayOf(2, 4), 6).print()

    s.kthSmallestProduct(intArrayOf(-100000, 100000), intArrayOf(-100000, 100000), 1).print()
}

class Solution5887 {
    fun kthSmallestProduct(nums1: IntArray, nums2: IntArray, k: Long): Long {

        val arr1 = if (nums1.size < nums2.size) nums1 else nums2
        val arr2 = if (nums1.size >= nums2.size) nums1 else nums2

        val reversed = arr2.reversed().toIntArray()

        // 计算比 <= mid的值有多少个，刚好为K个的mid即所求
        fun count(mid: Long): Long {
            var c = 0L
            arr1.forEach { n1 ->
                if (n1 >= 0) {
                    c += arr2.biLastIndexOf { n2 ->
                        n1.toLong() * n2.toLong() <= mid
                    } + 1
                } else {
                    c += reversed.biLastIndexOf { n2 ->
                        n1.toLong() * n2.toLong() <= mid
                    } + 1
                }
            }
            return c
        }

        val edges = arrayOf(
            arr1.first().toLong() * arr2.first(),
            arr1.last().toLong() * arr2.first(),
            arr1.first().toLong() * arr2.last(),
            arr1.last().toLong() * arr2.last(),
        )
        var left = edges.minOrNull()!! - 1
//        var left = edges.min()!! - 1
        var right = edges.maxOrNull()!! + 1
//        var right = edges.max()!! + 1

        while (left + 1 < right) {
            val mid = (left + right) / 2
            when {
                count(mid) >= k -> right = mid
                else -> left = mid
            }
        }
        return when {
            count(left) == k -> left
            count(right) == k -> right
            else -> right
        }
    }
}