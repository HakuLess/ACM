package leetcode.contest.c435

import kotlin.math.abs

class SolutionB {
    fun maxDistance(s: String, k: Int): Int {
        var x = 0
        var y = 0
        var ans = 0

        var a = 0
        var b = 0
        var c = 0
        var d = 0

        for (i in s.indices) {
            when (s[i]) {
                'N' -> {
                    y += 1
                    a++
                }
                'S' -> {
                    y -= 1
                    b++
                }
                'E' -> {
                    x += 1
                    c++
                }
                'W' -> {
                    x -= 1
                    d++
                }
            }

            var cur = abs(x) + abs(y)
            val diff = minOf(a, b) + minOf(c, d)
            cur += minOf(diff, k) * 2

            ans = maxOf(ans, cur)
        }

        return ans
    }
}