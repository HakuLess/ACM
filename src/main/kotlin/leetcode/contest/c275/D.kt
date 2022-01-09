package leetcode.contest.c275

import utils.print
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    s.earliestFullBloom(intArrayOf(1, 4, 3), intArrayOf(2, 3, 1)).print()
    s.earliestFullBloom(intArrayOf(1, 2, 3, 2), intArrayOf(2, 1, 2, 1)).print()
    s.earliestFullBloom(intArrayOf(5, 5), intArrayOf(5, 5)).print()
}

class SolutionD {
    fun earliestFullBloom(plantTime: IntArray, growTime: IntArray): Int {
        val arr = ArrayList<Pair<Int, Int>>()
        for (i in plantTime.indices) {
            arr.add(Pair(plantTime[i], growTime[i]))
        }
        arr.sortBy { -it.second }
        var day = 0
        var ans = 0
        for (i in arr.indices) {
            day += arr[i].first
            ans = maxOf(ans, day + arr[i].second)
        }
        return ans
    }
}