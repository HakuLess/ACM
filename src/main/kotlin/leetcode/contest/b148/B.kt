package leetcode.contest.b148

import kotlin.math.abs

class SolutionB {
    fun minCost(arr: IntArray, brr: IntArray, k: Long): Long {
        var ans0 = 0L
        for (i in arr.indices) {
            ans0 += abs(arr[i] - brr[i])
        }

        var ans1 = k
        arr.sort()
        brr.sort()
        for (i in arr.indices) {
            ans1 += abs(arr[i] - brr[i])
        }

        return minOf(ans0, ans1)
    }
}