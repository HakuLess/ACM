package leetcode.contest.c262

import utils.print
import java.util.*
import kotlin.math.abs

fun main() {
    val s = Solution5897()
//    s.minimumDifference(intArrayOf(3, 9, 7, 3)).print()
//    s.minimumDifference(intArrayOf(-36, 36)).print()
    s.minimumDifference(intArrayOf(42, 41, 59, 43, 69, 67)).print()
}

class Solution5897 {
    // 折半法
    // size为30的，先左15 右15拆
    fun minimumDifference(nums: IntArray): Int {
        val n = nums.size / 2

        fun helper(arr: Array<TreeSet<Int>>, offset: Int) {
            for (mask in 0 until (1 shl n)) {
                var cur = 0
                var count = 0
                // 该状态下的总和及总count
                for (i in 0 until n) {
                    if (mask and (1 shl i) != 0) {
                        count++
                        cur += nums[i + offset]
                    }
                }
                arr[count].add(cur)
            }
        }

        val leftArr = Array<TreeSet<Int>>(n + 1) { TreeSet() }
        val rightArr = Array<TreeSet<Int>>(n + 1) { TreeSet() }

        helper(leftArr, 0)
        helper(rightArr, n)

        var ans = Int.MAX_VALUE
        val sum = nums.sum()
        for (i in leftArr.indices) {
            leftArr[i].forEach { left ->
                val j = n - i
                rightArr[j].ceiling(sum / 2 - left)?.let { right ->
                    ans = minOf(ans, abs((left + right) * 2 - sum))
                }
                rightArr[j].floor(sum / 2 - left)?.let { right ->
                    ans = minOf(ans, abs((left + right) * 2 - sum))
                }
            }
        }

        return ans
    }
}