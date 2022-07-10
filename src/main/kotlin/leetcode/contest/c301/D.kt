package leetcode.contest.c301

import utils.print

fun main() {
    val s = SolutionD()
    s.idealArrays(2, 5).print()
    s.idealArrays(5, 3).print()
//    s.idealArrays(5878, 2900).print()
    s.idealArrays(9767, 9557).print()
}

// todo 组合数
class SolutionD {
    fun idealArrays(n: Int, maxValue: Int): Int {
        val mod = 1000000007L

        val primes = Array<ArrayList<Int>>(maxValue + 1) { arrayListOf() }
        for (i in 1..maxValue) {
            for (j in i..maxValue) {
                if (j % i == 0) {
                    primes[j].add(i)
                }
            }
        }

        // 长度 && 最终值
        var pre: LongArray
        var cur = LongArray(maxValue + 1) { 1 }

        for (i in 2..n) {
            pre = cur
            cur = LongArray(maxValue + 1) { 0 }
            for (j in 1..maxValue) {
                for (k in 1..maxValue / j) {
                    cur[j * k] += pre[k]
                    cur[j * k] %= mod
                }
            }
        }

        var ans = 0L
        for (i in 1..maxValue) {
            ans += cur[i]
            ans %= mod
        }
        return ans.toInt()
    }
}