package leetcode.contest.c280

import utils.Graph
import utils.km
import utils.print

fun main() {
    val s = SolutionD()
    s.maximumANDSum(intArrayOf(1, 2, 3, 4, 5, 6), 3).print()
}

// 状态压缩
// DP
// 二分图最大带权匹配
// KM算法
class SolutionD {
    fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
        val g = Graph(numSlots * 4)
        val arr = ArrayList<Int>()
        arr.addAll(nums.toList())
        while (arr.size != numSlots * 2) {
            arr.add(0)
        }
        for (i in arr.indices) {
            for (j in 0 until numSlots * 2) {
                g.addEdge(i, arr.size + j, arr[i] and j / 2 + 1)
            }
        }
        return g.km()
    }

//    fun maximumANDSum(nums: IntArray, numSlots: Int): Int {
//        var ans = 0
//        val dp = IntArray(1 shl numSlots * 2)
//        for (i in dp.indices) {
//            val c = Integer.bitCount(i)
//            if (c >= nums.size) continue
//            for (j in 0 until numSlots * 2) {
//                if (i and (1 shl j) == 0) {
//                    val k = i or (1 shl j)
//                    dp[k] = maxOf(dp[k], dp[i] + ((j / 2 + 1) and nums[c]))
//                    ans = maxOf(ans, dp[k])
//                }
//            }
//        }
//        return ans
//    }
}