package leetcode.contest.b141

import utils.print

fun main() {
    val s = SolutionB()
    s.minBitwiseArray(listOf(2, 3, 5, 7)).print()
}

class SolutionB {
    fun minBitwiseArray(nums: List<Int>): IntArray {
        val n = nums.size
        val ans = IntArray(n) { -1 }

        for (i in 0 until n) {
            val sb = StringBuilder(nums[i].toString(2))
            for (j in sb.indices) {
                if (sb[j] == '1') {
                    sb.setCharAt(j, '0')
                    val tmp = sb.toString().toInt(2)
//                    println("try $tmp with ${tmp or (tmp + 1)}")
                    if (tmp or (tmp + 1) == nums[i]) {
                        ans[i] = tmp
                        break
                    }
                    sb.setCharAt(j, '1')
                }
            }
        }
        return ans
    }
}