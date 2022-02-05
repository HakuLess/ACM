package leetcode.contest.b71

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.minimumDifference(intArrayOf(3, 1, 2)).print()
    s.minimumDifference(intArrayOf(7, 9, 5, 8, 1, 3)).print()
}

class SolutionD {
    fun minimumDifference(nums: IntArray): Long {
        val left = PriorityQueue<Long>(compareBy { -it })
        val middle = ArrayList<Long>()
        val right = PriorityQueue<Long>()
        for (i in nums.indices) {
            if (i < nums.size / 3) {
                left.add(nums[i].toLong())
            } else if (i < nums.size / 3 * 2) {
                middle.add(nums[i].toLong())
            } else {
                right.add(nums[i].toLong())
            }
        }
        val a = LongArray(nums.size / 3 + 1)
        val b = LongArray(nums.size / 3 + 1)
        var leftSum = left.sum()
        a[0] = leftSum
        for (i in 0 until nums.size / 3) {
            leftSum += middle[i]
            left.offer(middle[i])
            leftSum -= left.poll()
            a[i + 1] = leftSum
        }
        var rightSum = right.sum()
        b[nums.size / 3] = rightSum
        for (i in nums.size / 3 - 1 downTo 0) {
            rightSum += middle[i]
            right.offer(middle[i])
            rightSum -= right.poll()
            b[i] = rightSum
        }
        var ans = Long.MAX_VALUE
        for (i in a.indices) {
            ans = minOf(ans, a[i] - b[i])
        }
        return ans
    }
}