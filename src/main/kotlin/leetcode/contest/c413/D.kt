package leetcode.contest.c413

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.maximumSubarrayXor(intArrayOf(2, 8, 4, 32, 16, 1), "[[0,2],[1,4],[0,5]]".toGrid()).print()
    (62 xor 2).print()
}

class SolutionD {
    fun maximumSubarrayXor(nums: IntArray, queries: Array<IntArray>): IntArray {
        val prefixXOR = IntArray(nums.size + 1)
        val result = IntArray(queries.size)

        for (i in nums.indices) {
            prefixXOR[i + 1] = prefixXOR[i] xor nums[i]
        }
        prefixXOR.print()
        for (i in queries.indices) {
            val (l, r) = queries[i]
            var tmp = 1
            for (i in l..r) {
                for (j in i..r) {
                    tmp = maxOf(tmp, prefixXOR[i] xor prefixXOR[j + 1])
                    println("$tmp with ${prefixXOR[i]} xor ${prefixXOR[j]}")
                }
            }
            println("set $i with $tmp")
            result[i] = tmp
        }

        return result
    }
}