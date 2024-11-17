package leetcode.contest.c424

import utils.print
import kotlin.math.abs


fun main() {
    val s = SolutionD()
    s.minDifference(intArrayOf(1, 2, -1, 10, 8)).print()
}

class SolutionD {
    fun minDifference(nums: IntArray): Int {

        fun check(arr: ArrayList<IntArray>, l: Int, nums: IntArray): Boolean {
            var min = Int.MAX_VALUE
            var max = 0
            for (seg in arr) {
                if (seg[0] != -1) {
                    min = minOf(nums[seg[0]] + l, min)
                    max = maxOf(nums[seg[0]] - l, max)
                }
                if (seg[1] != -1) {
                    min = minOf(nums[seg[1]] + l, min)
                    max = maxOf(nums[seg[1]] - l, max)
                }
            }
            if (min >= max) return true
            for (seg in arr) {
                if (seg[0] == -1 || seg[1] == -1) continue
                val m1 = maxOf(nums[seg[0]], nums[seg[1]])
                val m2 = minOf(nums[seg[0]], nums[seg[1]])
                if (abs(m1 - min) <= l && abs(m2 - min) <= l || abs(m1 - max) <= l && abs(m2 - max) <= l) continue
                if (abs(max - m1) <= l && abs(min - m2) <= l && abs(min - max) <= l && seg[2] > 1) continue
                return false
            }
            return true
        }

        val arr = ArrayList<IntArray>()
        val n = nums.size
        var last = -1
        var min = 0
        var i = 0
        while (i < n) {
            if (nums[i] != -1) {
                if (i > 0 && nums[i - 1] != -1) min = maxOf(min, abs(nums[i] - nums[i - 1]))
                last = i
                i++
                continue
            }
            var p = i
            while (p < n && nums[p] == -1) p++
            val cnt = p - i
            if (p == n) {
                arr.add(intArrayOf(last, -1, cnt))
                break
            } else {
                arr.add(intArrayOf(last, p, cnt))
            }
            i = p - 1
            i++
        }
        if (arr.size == 0) return min
        var l = min
        var r = 1e9.toInt()
        while (l < r) {
            val mid = l + (r - l) / 2
            val flag = check(arr, mid, nums)
            if (flag) {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}