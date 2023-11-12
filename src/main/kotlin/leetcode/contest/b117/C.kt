package leetcode.contest.b117

import utils.print

fun main() {
    val s = SolutionC()
    s.stringCount(4).print()
//    s.stringCount(5).print()
//    s.stringCount(10).print()
}

class SolutionC {
    fun stringCount(n: Int): Int {
        val mod = 1000000007L
        val p = Array(n + 1) { Array(2) { Array(2) { LongArray(3) } } }

        p[0][0][0][0] = 1

        for (i in 1..n) {
            for (l in 0 until 2) {
                for (t in 0 until 2) {
                    for (e in 0 until 3) {
                        // 第i个字符为非l,e,t的字符
                        p[i][l][t][e] = (p[i - 1][l][t][e] * 23) % mod
                        // 第i个字符为l
                        if (l != 0) {
                            p[i][l][t][e] = (p[i][l][t][e] + (p[i - 1][l - 1][t][e] + p[i - 1][l][t][e]) % mod) % mod
                        }
                        // 第i个字符为t
                        if (t != 0) {
                            p[i][l][t][e] = (p[i][l][t][e] + (p[i - 1][l][t - 1][e] + p[i - 1][l][t][e]) % mod) % mod
                        }
                        // 第i个字符为e
                        if (e != 0) {
                            p[i][l][t][e] = (p[i][l][t][e] + p[i - 1][l][t][e - 1]) % mod
                            if (e == 2) {
                                p[i][l][t][e] = (p[i][l][t][e] + p[i - 1][l][t][e]) % mod
                            }
                        }
                    }
                }
            }
        }
        return ((p[n][1][1][2] + mod) % mod).toInt()
    }
}