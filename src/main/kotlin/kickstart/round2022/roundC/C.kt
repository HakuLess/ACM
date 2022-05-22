package kickstart.round2022.roundC

import utils.print
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    val t = readLine()!!.trim().toInt()
    repeat(t) {
        val (n, l) = readLine()!!.split(" ").map { it.toInt() }
        var ants = ArrayList<IntArray>()
        repeat(n) {
            val (pos, dir) = readLine()!!.split(" ").map { it.toInt() }
            val ant = intArrayOf(pos, dir, it + 1, 0)
            ants.add(ant)
        }
        ants.sortBy { it[0] }
        val left = ArrayList<Int>()
        val right = ArrayList<Int>()
        for (i in ants.indices) {
            val ant = ants[i]
            if (ant[1] == 0) {
                // move left
                left.add(ant[0])
            } else {
                right.add(l - ant[0])
            }
        }

        val ans = ArrayList<Int>()
        ans.addAll(left)
        ans.addAll(right)
        for (i in ants.indices) {
            ants[i][3] = ans[i]
        }

        println("Case #${it + 1}: ${ants.sortedWith(compareBy({ it[3] }, { it[2] })).map { it[2] }.joinToString(" ")}")
    }
}