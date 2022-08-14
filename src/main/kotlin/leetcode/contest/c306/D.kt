package leetcode.contest.c306

import utils.print

fun main() {
    val s = SolutionD()
    s.countSpecialNumbers(20).print()
    s.countSpecialNumbers(5).print()
    s.countSpecialNumbers(135).print()
}
class SolutionD {
    fun countSpecialNumbers(n: Int): Int {
        return n - numDupDigitsAtMostN(n)
    }

    companion object {
        // f[l][r] 代表 i * (i + 1) * ... * (j - 1) * j
        var f = Array(10) { IntArray(10) }

        init {
            for (i in 1..9) {
                for (j in i..9) {
                    var cur = 1
                    for (k in i..j) cur *= k
                    f[i][j] = cur
                }
            }
        }
    }

    fun dp(x: Int): Int {
        var t = x
        val nums: MutableList<Int> = ArrayList()
        while (t != 0) {
            nums.add(t % 10)
            t /= 10
        }
        val n = nums.size
        if (n <= 1) return x + 1 // [0, 9]
        // 位数和 x 相同（res1 + res2）
        var ans = 0
        run {
            var i = n - 1
            var p = 1
            var s = 0
            while (i >= 0) {
                val cur = nums[i]
                var cnt = 0
                for (j in cur - 1 downTo 0) {
                    if (i == n - 1 && j == 0) continue
                    if (s shr j and 1 == 0) cnt++
                }
                val a = 10 - p
                val b = a - (n - p) + 1
                ans += if (b <= a) cnt * f[b][a] else cnt
                if (s shr cur and 1 == 1) break
                s = s or (1 shl cur)
                if (i == 0) ans++
                i--
                p++
            }
        }
        // 位数比 x 少（res3）
        ans += 10
        var i = 2
        var last = 9
        while (i < n) {
            val cur = last * (10 - i + 1)
            ans += cur
            last = cur
            i++
        }
        return ans
    }

    fun numDupDigitsAtMostN(n: Int): Int {
        return n + 1 - dp(n)
    }
}