package leetcode.contest.c272

import utils.lis
import utils.print

fun main() {
    val s = SolutionD()
    s.kIncreasing(intArrayOf(12, 6, 12, 6, 14, 2, 13, 17, 3, 8, 11, 7, 4, 11, 18, 8, 8, 3), 1).print()
    s.kIncreasing(intArrayOf(2, 2, 2, 2, 2, 1, 1, 4, 4, 3, 3, 3, 3, 3), 1).print()
}

class SolutionD {
    fun kIncreasing(arr: IntArray, k: Int): Int {
        val l = Array<ArrayList<Int>>(k) { ArrayList<Int>() }
        for (i in arr.indices) {
            l[i % k].add(arr[i])
        }
        var ans = 0
        for (i in 0 until k) {
            ans += l[i].size - l[i].toIntArray().lis(false)
        }
        return ans
    }
}