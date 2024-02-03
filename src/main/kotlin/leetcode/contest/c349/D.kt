package leetcode.contest.c349

import utils.*

fun main() {
    val s = SolutionD()
//    s.maximumSumQueries(intArrayOf(4, 3, 1, 2), intArrayOf(2, 4, 9, 5), "[[4,1],[1,3],[2,5]]".toGrid()).print()
//    s.maximumSumQueries(intArrayOf(31), intArrayOf(17), "[[1,79]".toGrid()).print()

    // 120
//    s.maximumSumQueries(intArrayOf(13, 67, 90, 92, 47), intArrayOf(52, 60, 69, 49, 73), "[[32,70]".toGrid()).print()

    // [-1,151]
    s.maximumSumQueries(
        intArrayOf(72, 88, 53, 63, 95, 46),
        intArrayOf(78, 56, 35, 72, 56, 63),
        "[[86,86],[24,8]]".toGrid()
    ).print()
}

// BIT Tree
// 按x 从大到小 直接遍历，不断更新y值
// 二维比较大小 双维度
// https://leetcode.cn/problems/maximum-sum-queries/description/
class SolutionD {
    fun maximumSumQueries(nums1: IntArray, nums2: IntArray, queries: Array<IntArray>): IntArray {
        // x, y, 是否为插入
        val l = ArrayList<Triple<Int, Int, Int>>()
        for (i in nums1.indices) {
            l.add(Triple(nums1[i], nums2[i], -1))
        }
        for (i in queries.indices) {
            l.add(Triple(queries[i][0], queries[i][1], i))
        }
        // 先执行插入
        l.sortWith(compareBy({ -it.first }, { it.third }))
        val root = SegmentTree<Int>(start = 1, end = Int.MAX_VALUE / 2, value = 0) { a, b ->
            maxOf(a, b)
        }

        val ans = IntArray(queries.size)
        l.forEach {
            if (it.third == -1) {
                // 执行插入
//                println("insert ${it.second} with ${it.first + it.second}")
                if (root.query(root, it.second, it.second) < it.first + it.second)
                    root.update(root, it.second, it.second, it.first + it.second)
            } else {
                // 执行查询
//                println("query ${it.third} with ${it.first} ${it.second}")
                ans[it.third] = (root.query(root, it.second, Int.MAX_VALUE / 2)).let {
                    if (it == 0) -1 else it
                }
            }
        }
        return ans
    }
}