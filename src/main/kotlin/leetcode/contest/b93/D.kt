package leetcode.contest.b93

import utils.print

fun main() {
    val s = SolutionD()
    s.minimumTotalCost(intArrayOf(1, 3, 2), intArrayOf(3, 3, 1)).print()
    s.minimumTotalCost(intArrayOf(3, 3, 1), intArrayOf(1, 3, 2)).print()
}

class SolutionD {
    fun minimumTotalCost(nums1: IntArray, nums2: IntArray): Long {
        val n = nums1.size
        var cnt = 0
        val vis = IntArray(n + 1)
        var x = 0
        var ans = 0L
        for (i in 0 until n) {
            val v = nums1[i]
            if (v == nums2[i]) {
                cnt++
                vis[v]++
                // 时刻更新最大值
                if (vis[v] > vis[x]) x = v
                ans += i
            }
        }
        var fd = vis[x] * 2 - cnt
        for (i in 0 until n) {
            if (fd <= 0) break
            val v = nums1[i]
            if (v == nums2[i]) continue
            if (nums2[i] == x || nums1[i] == x) continue;
            ans += i
            fd--
        }
        return if (fd > 0) -1 else ans
    }
}