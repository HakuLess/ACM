package leetcode.contest.c386

import utils.biMin
import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val s = SolutionD()
    // 6
//    s.earliestSecondToMarkIndices(intArrayOf(3, 2, 3), intArrayOf(1, 3, 2, 2, 2, 2, 3)).print()
    // 7
//    s.earliestSecondToMarkIndices(intArrayOf(0, 0, 1, 2), intArrayOf(1, 2, 1, 2, 1, 2, 1, 2)).print()
    // -1
//    s.earliestSecondToMarkIndices(intArrayOf(1, 2, 3), intArrayOf(1, 2, 3)).print()

    // 4
//    s.earliestSecondToMarkIndices(intArrayOf(0, 2), intArrayOf(1, 1, 2, 2, 1)).print()
    // 6
    s.earliestSecondToMarkIndices(intArrayOf(3, 1), intArrayOf(2, 2, 2, 2, 1, 2, 2)).print()
}

class SolutionD {
    fun earliestSecondToMarkIndices(nums: IntArray, changeIndices: IntArray): Int {

        val n = nums.size
        val m = changeIndices.size

        return biMin(l = 1L, r = m.toLong()) {
            val arr = IntArray(n + 1) { 0 }

            for (i in 0 until it.toInt()) {
                arr[changeIndices[i]]++
            }

            var need = 0
            for (i in 1..n) {
                // 没有在 changeIndices， 需要手动清除
                if (arr[i] == 0 || (changeIndices[it.toInt() - 1] == i && arr[i] == 1)) {
                    need += nums[i - 1] + 1
                } else {
                    need += 1 + (if (nums[i - 1] == 0) 0 else 1)
                }
            }

//            println("check0 $it: ${arr.joinToString()}")

//            val lst = changeIndices[it.toInt() - 1]

//            println("check1 $it with $need  ${arr[lst]}")

//            if (arr[lst] == 1) {
//                need++
//            }

//            println("$need with $it")

            return@biMin need.toLong() <= it
        }.toInt()
    }
}