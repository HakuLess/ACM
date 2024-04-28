package leetcode.contest.c395

import utils.print

fun main() {
    val s = SolutionD()
    // 1
    s.medianOfUniquenessArray(intArrayOf(1, 2, 3)).print()
    // 2
    s.medianOfUniquenessArray(intArrayOf(3, 4, 3, 4, 5)).print()
    // 2
    s.medianOfUniquenessArray(intArrayOf(4, 3, 5, 4)).print()

    // 4
    s.medianOfUniquenessArray(intArrayOf(91, 64, 76, 18, 61, 55, 46, 93, 65, 99)).print()
}

class SolutionD {
    fun medianOfUniquenessArray(nums: IntArray): Int {
        var low = 1L
        var high = nums.size.toLong()
        val n = nums.size.toLong()

        while (low < high) {
            val mid = 0L + low + (high - low) / 2
            if (countDistinct(nums, mid) >= 1L * (n * (n + 1) / 2 + 1) / 2) {
                high = mid
            } else {
                low = mid + 1
            }
        }

        if (countDistinct(nums, low - 1) == 1L * (n * (n + 1) / 2 + 1) / 2) {
            return (low - 1).toInt()
        }
        return low.toInt()
    }

    fun countDistinct(nums: IntArray, k: Long): Long {
        val occurrences = mutableMapOf<Int, Int>()
        var left = 0
        var count = 0
        var result = 0L

        for (right in nums.indices) {
            occurrences[nums[right]] = occurrences.getOrDefault(nums[right], 0) + 1
            if (occurrences[nums[right]] == 1) {
                count++
            }
            while (count > k) {
                occurrences[nums[left]] = occurrences[nums[left]]!! - 1
                if (occurrences[nums[left]] == 0) {
                    count--
                }
                left++
            }
            result += right - left + 1
        }
        return result
    }

    fun force(nums: IntArray): Int {
        val l = ArrayList<Int>()
        for (i in nums.indices) {
            val set = HashSet<Int>()
            for (j in i until nums.size) {
                set.add(nums[j])
                l.add(set.size)
            }
        }
        l.sort()
//        l.joinToString().print()
        return l[l.size / 2]
    }
}