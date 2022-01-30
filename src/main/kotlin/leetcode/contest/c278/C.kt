package leetcode.contest.c278

import utils.print
import utils.quickPower

fun main() {
    val s = SolutionC()
    s.subStrHash("leetcode", 7, 20, 2, 0).print()
    s.subStrHash("fbxzaad", 31, 100, 3, 32).print()
    s.subStrHash("xmmhdakfursinye", 96, 45, 15, 21).print()
}

class SolutionC {
    fun subStrHash(s: String, power: Int, modulo: Int, k: Int, hashValue: Int): String {
        var cur = 0L
        var l = 0
        var ans = ""
        for (i in s.indices.reversed()) {
            cur *= power
            cur += s[i] - 'a' + 1
            l++
            if (l > k) {
                cur -= (s[i + k] - 'a' + 1) * quickPower(power.toLong(), k.toLong(), modulo.toLong())
                cur %= modulo
                cur = (cur + modulo) % modulo
            }
            if (l >= k && cur % modulo == hashValue.toLong()) {
                ans = s.substring(i, i + k)
            }
            cur %= modulo
        }
        return ans
    }
}