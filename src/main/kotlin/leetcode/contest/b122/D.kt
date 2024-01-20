package leetcode.contest.b122

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumCost(intArrayOf(1, 3, 2, 6, 4, 2), 3, 3).print()
    // 15
    s.minimumCost(intArrayOf(10, 1, 2, 2, 2, 1), 4, 3).print()

    s.minimumCost(intArrayOf(10, 8, 18, 9), 3, 1).print()
}

class SolutionD {
    fun minimumCost(nums: IntArray, k: Int, dist: Int): Long {
        var ans = Long.MAX_VALUE

        // 中间的数组
        var mid = nums.lastIndex

        val arr = TopKSumSortedList()
        arr.k = k - 2

        // 遍历最后一个数组
        for (right in nums.lastIndex downTo k - 1) {
            var tmp = 0L
            tmp += nums[0]
            tmp += nums[right]

            // 中间的数组
            while (mid != 0 && right - mid <= dist) {
                arr.insert(nums[mid].toLong())
                mid--
            }

            arr.remove(nums[right].toLong())
            tmp += arr.sum

            ans = minOf(ans, tmp)
        }
        return ans
    }

    // 最小K个数字的和
    class TopKSumSortedList {

        val valueList = ArrayList<Long>()

        var k = 0

        var sum = 0L

        fun insert(value: Long) {
            val index = valueList.binarySearch(value)
            if (index < 0) {
                // 无当前值，按index返回值插入
                valueList.add(-index - 1, value)
            } else {
                valueList.add(index, value)
            }

            val insertIndex = valueList.binarySearch(value)
            if (insertIndex < k) {
                sum += valueList[insertIndex]
                if (valueList.size > k) {
                    sum -= valueList[k]
                }
            }
        }

        fun remove(value: Long) {
            val index = valueList.binarySearch(value)
            valueList.removeAt(index)

            if (index < k) {
                sum -= value
                if (valueList.size >= k) {
                    sum += valueList[k - 1]
                }
            }
        }
    }
}