package leetcode.contest.c349

import utils.SortedList
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maximumSumQueries(intArrayOf(4, 3, 1, 2), intArrayOf(2, 4, 9, 5), "[[4,1],[1,3],[2,5]]".toGrid()).print()
    s.maximumSumQueries(intArrayOf(31), intArrayOf(17), "[[1,79]".toGrid()).print()

    // 120
    s.maximumSumQueries(intArrayOf(13, 67, 90, 92, 47), intArrayOf(52, 60, 69, 49, 73), "[[32,70]".toGrid()).print()
}

// todo BIT Tree
// 按x 从大到小 直接遍历，不断更新y值
class SolutionD {
    fun maximumSumQueries(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): IntArray {
        val rectangles = Array<IntArray>(nums1.size) { IntArray(2) }
        for (i in nums1.indices) {
            rectangles[i][0] = nums1[i]
            rectangles[i][1] = nums2[i]
        }
        rectangles.sortWith(compareBy({ -it[0] }, { -it[1] }))

        val n = queries.size
        val ids = IntRange(0, n - 1).toList().toTypedArray()
        // 坐标x 由大大小排序
        // 对id进行排序
        ids.sortBy { -queries[it][0] }

        val ans = IntArray(n) { -1 }
        val cur = SortedList<Int>()
        var i = 0
        var max = -1
        for (id in ids) {
            while (i in rectangles.indices && rectangles[i][0] >= queries[id][0]) {
                // 增加纵坐标
                cur.insert(rectangles[i][1])
                max = maxOf(max, rectangles[i][0] + rectangles[i][1])
                i++
            }
            val y = queries[id][1]
            if (cur.largerThanAndEqual(y) >= 1) {
                ans[id] = maxOf(ans[id], max)
            }
        }
        return ans
    }
}