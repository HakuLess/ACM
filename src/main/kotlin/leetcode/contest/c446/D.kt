package leetcode.contest.c446

import utils.print
import utils.toGrid

fun main() {
    val s = SolutionD()
    s.resultArray(intArrayOf(1, 2, 3, 4, 5), 3, "[[2,2,0,2],[3,3,3,0],[0,1,0,1]]".toGrid()).print()
}

class SolutionD {
    fun resultArray(nums: IntArray, k: Int, queries: Array<IntArray>): IntArray {

        fun getSimpleResult(nums: IntArray, k: Int): LongArray {
            val dp = LongArray(k) { 0L }

            var mod = 1L
            for (num in nums) {
                mod = (mod * num) % k
                dp[mod.toInt()]++
            }

            return dp
        }

        val ans = ArrayList<Int>()
        for (query in queries) {
            val (index, value, start, x) = query

            nums[index] = value
            val arr = if (start >= 0) {
                nums.slice(IntRange(start = start, endInclusive = nums.lastIndex)).toIntArray()
            } else {
                nums
            }
            val cur = getSimpleResult(arr, k)

//            println("get Cur %${k} = ${cur.joinToString()}")
            ans.add(cur[x].toInt())
        }

        return ans.toIntArray()
    }
}