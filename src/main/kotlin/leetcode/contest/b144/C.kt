package leetcode.contest.b144

import utils.print
import utils.toGrid
import java.util.*


fun main() {
    val s = SolutionC()
    // 4
    s.maxRemoval(intArrayOf(1, 2), "[[1,1],[0,0],[1,1],[1,1],[0,1],[0,0]]".toGrid()).print()
    // 8
    s.maxRemoval(
        intArrayOf(0, 4, 4, 0),
        "[[0,0],[3,3],[0,2],[3,3],[0,2],[0,1],[1,1],[1,2],[0,1],[2,3],[0,1],[1,2]]".toGrid()
    ).print()
}

class SolutionC {
    fun maxRemoval(nums: IntArray, queries: Array<IntArray>): Int {

        // 按到li排序，我是从左到右遍历，所以将能够生效的位置计算出来
        queries.sortWith(compareBy({ it[0] }, { it[1] }))

        // 模拟加油站的思路
        val effective = PriorityQueue<Int>(compareBy { queries[it][1] })

        val potential = PriorityQueue<Int>(compareBy { -queries[it][1] })

        var pos = 0
        var used = 0
        for (i in nums.indices) {
            // 把可能生效的范围加入
            while (pos < queries.size && queries[pos][0] <= i) {
                potential.add(pos++)
            }
            // 把已经失效的剔除
            while (!effective.isEmpty() && queries[effective.peek()][1] < i) {
                effective.poll()
            }
            // 当前数字没有被清零，就尽量把范围大的代入进入
            while (effective.size < nums[i] && !potential.isEmpty() && queries[potential.peek()][1] >= i) {
                used += 1
                effective.add(potential.poll())
            }
            // 不符合要求，返回-1
            if (effective.size < nums[i]) {
                return -1
            }
        }
        return queries.size - used
    }
}