package leetcode.lccup.round2023.spring.team

import utils.biMax
import utils.print
import utils.toGrid

fun main() {
    val s = SolutionB()
    s.rampartDefensiveLine("[[0,3],[4,5],[7,9]]".toGrid()).print()
    s.rampartDefensiveLine("[[1,2],[5,8],[11,15],[18,25]]".toGrid()).print()
}

class SolutionB {
    fun rampartDefensiveLine(rampart: Array<IntArray>): Int {
        val l = ArrayList<Int>()
        for (i in 1 until rampart.size) {
            l.add(rampart[i][0] - rampart[i - 1][1])
        }
//        l.joinToString().print()
        return biMax(1L, Int.MAX_VALUE.toLong()) {
            val item = it.toInt()
            val c = ArrayList<Int>()
            c.addAll(l)
            for (i in 0 until c.lastIndex) {
                val left = minOf(item, c[i])
                c[i] -= left
                c[i + 1] -= item - left
                if (c[i + 1] < 0) {
                    return@biMax false
                }
            }
            true
        }.toInt()
    }
}