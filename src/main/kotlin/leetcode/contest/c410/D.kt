package leetcode.contest.c410

import utils.print

fun main() {
    val s = SolutionD()
    s.countOfPairs(intArrayOf(2, 3, 2)).print()
    s.countOfPairs(intArrayOf(5, 5, 5, 5)).print()
    val arr = ArrayList<Int>()
    repeat(300) {
        arr.add(1000)
    }
    arr.joinToString(",").print()
    s.countOfPairs(arr.toIntArray()).print()
}

class SolutionD {
    fun countOfPairs(nums: IntArray): Int {
        val mod = 1000000007L
        var dp = LongArray(1001)
        var isFirst = true
        var last = -1
        for (n in nums) {
            val nextDp = LongArray(n + 1)
            if (isFirst) {
                for (i in 0..n) {
                    nextDp[i] = 1
                }
            } else {
                var sum = 0L
                var st = 0
                for (i in 0..n) {
                    val curArr2 = n - i
                    while (st <= minOf(i, last) && last - st >= curArr2) {
                        sum = (sum + dp[st]) % mod
                        ++st
                    }
                    nextDp[i] = sum
                }
            }
            isFirst = false
            dp = nextDp
            last = n
        }
        var res = 0L
        for (n in dp) {
            res = (res + n) % mod
        }
        return (res % mod).toInt()
    }
}