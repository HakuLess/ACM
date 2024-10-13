package leetcode.contest.c419

import utils.print

fun main() {
    val s = SolutionA()
    s.findXSum(intArrayOf(1, 1, 2, 2, 3, 4, 2, 3), 6, 2).print()
}

class SolutionA {
    fun findXSum(nums: IntArray, k: Int, x: Int): IntArray {
        val map = mutableMapOf<Int, Int>()
        val ans = ArrayList<Int>()
        for (i in nums.indices) {
            map[nums[i]] = map.getOrDefault(nums[i], 0) + 1
            val sortedFreq = map.entries.sortedWith(compareBy({ -it.value }, { -it.key }))
//            println("$i: ${sortedFreq.joinToString()}")
            if (i >= k - 1) {
//                sortedFreq.take(x).joinToString().print()
                val sum = sortedFreq.take(x).sumOf { it.key * it.value }
                ans.add(sum)
            }
            if (i >= k - 1) {
                map[nums[i - k + 1]] = map[nums[i - k + 1]]!! - 1
            }
        }
        return ans.toIntArray()
    }
}