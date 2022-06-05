package leetcode.contest.c296

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.arrayChange(intArrayOf(1, 2, 4, 6), "[[1,3],[4,7],[6,1]]".toGrid()).print()
//    s.arrayChange(intArrayOf(1, 2), "[[1,3],[2,1],[3,2]]".toGrid()).print()
}

// 注意条件 N个不同的数 再每一次执行都保证N个数不同
class SolutionC {
    fun arrayChange(nums: IntArray, operations: Array<IntArray>): IntArray {
        val map = HashMap<Int, Int>()
        for (i in nums.indices) {
            map[nums[i]] = i
        }
        for (p in operations) {
            nums[map[p[0]]!!] = p[1]
            map[p[1]] = map[p[0]]!!
        }
        return nums
    }
}