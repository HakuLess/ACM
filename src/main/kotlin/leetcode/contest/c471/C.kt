package leetcode.contest.c471

import utils.print

fun main() {
    val s = SolutionC()
    // 4
    s.longestBalanced("abbac").print()
}

class SolutionC {
    fun longestBalanced(s: String): Int {

        var ca = 0
        var cb = 0
        var cc = 0
        var ans = 0

        val mapTriple = mutableMapOf<Pair<Int, Int>, Int>()
        val mapAB = mutableMapOf<Pair<Int, Int>, Int>()
        val mapBC = mutableMapOf<Pair<Int, Int>, Int>()
        val mapAC = mutableMapOf<Pair<Int, Int>, Int>()

        mapTriple[Pair(0, 0)] = -1
        mapAB[Pair(0, 0)] = -1
        mapBC[Pair(0, 0)] = -1
        mapAC[Pair(0, 0)] = -1

        var tmp = 0

        for (i in s.indices) {
            when (s[i]) {
                'a' -> ca++
                'b' -> cb++
                'c' -> cc++
            }

            val dab = ca - cb
            val dac = ca - cc
            val dbc = cb - cc

            val keyTriple = Pair(dab, dac)
            if (keyTriple in mapTriple) {
                val prev = mapTriple[keyTriple]!!
                ans = maxOf(ans, i - prev)
            } else {
                mapTriple[keyTriple] = i
            }

            val keyAB = Pair(dab, cc)
            if (keyAB in mapAB) {
                val prev = mapAB[keyAB]!!
                ans = maxOf(ans, i - prev)
            } else {
                mapAB[keyAB] = i
            }

            val keyBC = Pair(dbc, ca)
            if (keyBC in mapBC) {
                val prev = mapBC[keyBC]!!
                ans = maxOf(ans, i - prev)
            } else {
                mapBC[keyBC] = i
            }

            val keyAC = Pair(dac, cb)
            if (keyAC in mapAC) {
                val prev = mapAC[keyAC]!!
                ans = maxOf(ans, i - prev)
            } else {
                mapAC[keyAC] = i
            }

            if (i > 0 && s[i] == s[i - 1]) {
                tmp++
            } else {
                tmp = 1
            }
            ans = maxOf(ans, tmp)
        }

        return ans
    }
}