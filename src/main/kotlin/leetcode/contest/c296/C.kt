package leetcode.contest.c296

import utils.print
import utils.printInt
import utils.toGrid

fun main() {
    val s = SolutionC()
    s.arrayChange(intArrayOf(1, 2, 4, 6), "[[1,3],[4,7],[6,1]]".toGrid()).print()
    s.arrayChange(intArrayOf(1, 2), "[[1,3],[2,1],[3,2]]".toGrid()).print()

    s.arrayChange(intArrayOf(1), "[[1,2],[2,3],[3,1000000],[1000000,1]]".toGrid()).print()
}

class SolutionC {
    fun arrayChange(nums: IntArray, operations: Array<IntArray>): IntArray {
        val map = HashMap<Int, ArrayList<Int>>()
        for (i in nums.indices) {
            map[nums[i]] = map.getOrDefault(nums[i], arrayListOf())
            map[nums[i]]!!.add(i)
        }
        operations.forEach {
            map[it[0]] = map.getOrDefault(it[0], arrayListOf())
            map[it[1]] = map.getOrDefault(it[1], arrayListOf())

            map[it[1]]!!.addAll(map[it[0]]!!)
            map[it[0]]!!.clear()
        }
        val ans = IntArray(nums.size)
        for (i in map.keys) {
            map[i]?.forEach {
                ans[it] = i
            }
        }
        return ans
    }
}