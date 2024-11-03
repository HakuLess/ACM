package leetcode.contest.c422

import utils.permute
import utils.permuteUnique
import utils.print

fun main() {
    val s = SolutionD()
    s.countBalancedPermutations("123").print()
    s.countBalancedPermutations("112").print()
    s.countBalancedPermutations("12345").print()
    s.countBalancedPermutations("7160").print()
}

class SolutionD {
    fun countBalancedPermutations(num: String): Int {

        val mod = 1_000_000_007L
        val a = num.length / 2
        val b = num.length - a

        val arr = num.map { it - '0' }.toIntArray()

        val seen = HashMap<String, Long>()
        fun dfs(index: Int, leftA: Int, leftB: Int, sumA: Int, sumB: Int): Long {
            if (leftA == 0 && leftB == 0) {
                return if (sumA == sumB) 1L else 0L
            }

            val key = "$leftA, $leftB, $sumA, $sumB"
            if (key in seen) return seen[key]!!
            var tmp = 0L
            if (leftA > 0) {
                tmp += dfs(index + 1, leftA - 1, leftB, sumA + arr[index], sumB)
                tmp %= mod
            }
            if (leftB > 0) {
                tmp += dfs(index + 1, leftA, leftB - 1, sumA, sumB + arr[index])
                tmp %= mod
            }
            return tmp.also {
                seen[key] = it
            }
        }

        var tmp = dfs(0, a, b, 0, 0)
//        for (i in 1..a) {
//            tmp *= i
//            tmp %= mod
//        }
//        for (i in 1..b) {
//            tmp *= i
//            tmp %= mod
//        }

        return (tmp % mod).toInt()
    }
}