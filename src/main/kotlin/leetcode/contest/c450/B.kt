package leetcode.contest.c450

import utils.print
import utils.printInt
import utils.swap

fun main() {
    val s = SolutionB()
    s.minSwaps(intArrayOf(18, 43, 34, 16)).print()
    // 2
    s.minSwaps(intArrayOf(625468152, 191921893, 821181574)).print()
}

class SolutionB {
    fun minSwaps(nums: IntArray): Int {
        val sorted = nums.sortedWith(compareBy({ it.toString().map { it - '0' }.sum() }, { it }))
//        sorted.joinToString().print()

        val indexMap = nums.withIndex().associate { it.value to it.index }
//        indexMap.printInt()

        val visited = BooleanArray(nums.size)
        var ans = 0

        for (i in nums.indices) {
            if (visited[i] || nums[i] == sorted[i]) continue

            var cycleSize = 0
            var j = i
            while (!visited[j]) {
                visited[j] = true
                val nextValue = sorted[j]
                j = indexMap[nextValue]!!
                cycleSize++
            }

            ans += cycleSize - 1
        }

        return ans
    }
}