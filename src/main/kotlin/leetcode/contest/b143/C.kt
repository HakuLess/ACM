package leetcode.contest.b143

import utils.SegmentTree
import utils.SegmentTreeGPT
import utils.SegmentTreeMax
import utils.print

fun main() {
    val s = SolutionC()
    s.maxFrequency(intArrayOf(14, 67, 36, 118, 4), 62, 3).print()

    val segmentTree = SegmentTreeMax()

    // 示例：对区间 [10^8 ,10^9 ] 增加5
    segmentTree.updateRange(100000000, 1000000000.toInt(), 5)

    // 查询区间 [10^8 ,10^9 ] 的最大值
    println(segmentTree.queryMax(100000000.toInt(),1000000000.toInt())) // 输出结果，应该是5

    // 示例：对区间 [5*10^8 ,7*10^8 ] 增加3
    segmentTree.updateRange(500000000.toInt(),700000000.toInt(),3)

    // 查询区间 [5*10^8 ,7*10^8 ] 的最大值
    println(segmentTree.queryMax(500000000.toInt(),700000000.toInt())) // 输出结果，应该是8
}

class SolutionC {
    fun maxFrequency(nums: IntArray, k: Int, numOperations: Int): Int {
//        val n = 1000000000
        val root = SegmentTreeMax()

        nums.sort()
        var ans = 0

        nums.forEach {
            root.updateRange(it, it, 1)
        }

        for (i in nums.indices) {
            val it = nums[i]
            root.updateRange(it, it, -1)
            root.updateRange(it - k, it + k, 1)

            ans = maxOf(ans, root.queryMax(nums.minOrNull()!!, nums.maxOrNull()!!).toInt())

            if (i >= numOperations) {
                val preIt = nums[i - numOperations]
                root.updateRange(preIt - k, preIt + k, -1)
                root.updateRange(preIt, preIt, 1)
            }
        }
        return ans
    }
}