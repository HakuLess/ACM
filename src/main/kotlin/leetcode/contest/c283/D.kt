package leetcode.contest.c283

import utils.gcd
import utils.lcm
import utils.print
import java.util.*

fun main() {
    val s = SolutionD()
    s.replaceNonCoprimes(intArrayOf(6, 4, 3, 2, 7, 6, 2)).joinToString().print()
    s.replaceNonCoprimes(intArrayOf(2, 2, 1, 1, 3, 3, 3)).joinToString().print()
}

class SolutionD {
    fun replaceNonCoprimes(nums: IntArray): List<Int> {
        val ans = Stack<Int>()
        for (i in nums.indices) {
            ans.push(nums[i])
            while (ans.size >= 2) {
                val a = ans.pop()
                val b = ans.pop()
                if (gcd(a, b) > 1) {
                    ans.push(lcm(a, b))
                } else {
                    ans.push(b)
                    ans.push(a)
                    break
                }
            }
        }
        return ans
    }
}