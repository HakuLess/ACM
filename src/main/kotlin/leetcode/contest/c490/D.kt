package leetcode.contest.c490

import utils.print
import java.math.BigInteger

fun main() {
    val s = SolutionD()
    s.countSequences(intArrayOf(2, 3, 2), 6).print()
    s.countSequences(intArrayOf(4, 6, 3), 2).print()
    s.countSequences(intArrayOf(1, 5), 1).print()
}

class SolutionD {
    fun countSequences(nums: IntArray, k: Long): Int {

        val seen = HashMap<Triple<Int, BigInteger, BigInteger>, Long>()
        val target = BigInteger.valueOf(k)

        fun gcd(a: BigInteger, b: BigInteger): BigInteger {
            return a.gcd(b)
        }

        fun dfs(i: Int, num: BigInteger, den: BigInteger): Long {
            if (i == nums.size) {
                return if (num == target && den == BigInteger.ONE) 1 else 0
            }

            val key = Triple(i, num, den)
            seen[key]?.let { return it }

            val cur = BigInteger.valueOf(nums[i].toLong())
            var result = 0L

            // 乘法
            run {
                var n2 = num.multiply(cur)
                var d2 = den
                val g = gcd(n2, d2)
                n2 = n2.divide(g)
                d2 = d2.divide(g)
                result += dfs(i + 1, n2, d2)
            }

            // 除法
            run {
                var n2 = num
                var d2 = den.multiply(cur)
                val g = gcd(n2, d2)
                n2 = n2.divide(g)
                d2 = d2.divide(g)
                result += dfs(i + 1, n2, d2)
            }

            // 跳过
            result += dfs(i + 1, num, den)

            seen[key] = result
            return result
        }

        return dfs(0, BigInteger.ONE, BigInteger.ONE).toInt()
    }
}