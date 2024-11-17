package leetcode.contest.c424

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionD()
    s.minDifference(intArrayOf(1, 2, -1, 10, 8)).print()
}

class SolutionD {
    fun minDifference(nums: IntArray): Int {

        val l = ArrayList<Item>()
        var ans = 0
        for (i in nums.indices) {
            if (nums[i] != -1) {
                if (i - 1 in nums.indices && nums[i - 1] != -1) {
                    ans = maxOf(ans, abs(nums[i] - nums[i - 1]))
                }

                if (i + 1 in nums.indices && nums[i + 1] != -1) {
                    ans = maxOf(ans, abs(nums[i] - nums[i + 1]))
                }
            }
        }

        var lst = -1
        var cnt = 0
        for (i in nums.indices) {
            if (nums[i] == -1) {
                cnt++
            }

            if (nums[i] != -1) {
                if (cnt != 0) {
                    l.add(Item(lst, nums[i], cnt))
                }
                lst = nums[i]
                cnt = 0
            }
        }

        l.joinToString().print()

        l.forEach {

        }
        return ans
    }
}

// 左侧值 右侧值 -1个数
data class Item(val left: Int, val right: Int, val num: Int)