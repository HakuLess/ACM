package leetcode.contest.c396

import utils.print

fun main() {
    val s = SolutionD()
    // 20
    s.minCostToEqualizeArray(intArrayOf(1, 14, 14, 15), 2, 1).print()

    // 15
    s.minCostToEqualizeArray(intArrayOf(9, 2, 10), 3, 1).print()

    // 90
    s.minCostToEqualizeArray(intArrayOf(60, 19, 53, 31, 57), 60, 2).print()

}

class SolutionD {
    fun minCostToEqualizeArray(nums: IntArray, cost1: Int, cost2: Int): Int {
        val mod = 1000000007L
        val max = nums.maxOrNull()!!

        val l = ArrayList<Int>()
        var sum = 0L
        for (i in nums.indices) {
            val diff = max - nums[i]
            sum += diff
            l.add(diff)
        }
        l.sortDescending()
//        l.joinToString().print()

        if (cost1 * 2 <= cost2) {
            return (sum * cost1 % mod).toInt()
        }

        var ans = Long.MAX_VALUE
        for (add in 0..max) {
            var tmp = 0L
            if (l[0] <= sum / 2) {
                tmp += sum / 2 * cost2
                tmp += sum % 2 * cost1
                ans = minOf(ans, tmp)
            } else {
                // 先用cost2消耗
                tmp += (sum - l[0]) * cost2
                tmp += (l[0] - (sum - l[0])) * cost1
                ans = minOf(ans, tmp)
            }

            l[0]++
            sum += nums.size
        }


        return (ans % mod).toInt()
    }
}