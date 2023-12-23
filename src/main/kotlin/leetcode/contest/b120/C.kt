package leetcode.contest.b120

import utils.biFirstIndexOf
import utils.print

fun main() {
    val s = SolutionC()
    s.incremovableSubarrayCount(intArrayOf(1, 2, 3, 4)).print()
    s.incremovableSubarrayCount(intArrayOf(6, 5, 7, 8)).print()
    // 3
    s.incremovableSubarrayCount(intArrayOf(8, 7, 6, 6)).print()
    // 3
    s.incremovableSubarrayCount(intArrayOf(7, 3)).print()
}

class SolutionC {
    fun incremovableSubarrayCount(nums: IntArray): Long {
        // 左侧连续递增
        var l = 1
        for (i in nums.indices) {
            if (i + 1 in nums.indices && nums[i + 1] > nums[i]) {
                l++
            } else {
                break
            }
        }
        // 右侧连续递增
        var r = 1
        for (i in nums.indices.reversed()) {
            if (i - 1 in nums.indices && nums[i] > nums[i - 1]) {
                r++
            } else {
                break
            }
        }

//        println("l is $l  r is $r")

        // 整体连通
        if (l == nums.size) {
            return 1L * (l + 1) * l / 2
        }

        var ans = 0L
        ans += 1L * r
        ans += 1L * l
        val take = ArrayList<Int>()
        take.addAll(nums.takeLast(r))
        for (i in 0 until l) {
            var size = take.biFirstIndexOf { it > nums[i] }
            if (size == -1) {
                size = take.size
            }
            ans += take.size - size
//            println("count ${take.count { it > nums[i] }} with ${take.size} - $size")
        }
        ans += 1L
        return ans
    }
}