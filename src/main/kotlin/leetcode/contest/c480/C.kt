package leetcode.contest.c480

import utils.print
import kotlin.math.abs

fun main() {
    val s = SolutionC()
    s.minMoves(intArrayOf(5, 1, -4)).print()
    s.minMoves(intArrayOf(1, 2, -5, 2)).print()
    s.minMoves(intArrayOf(-3, 2)).print()
}

class SolutionC {
    fun minMoves(balance: IntArray): Long {
        val n = balance.size

        var negIndex = -1
        var totalSum = 0L

        for (i in 0 until n) {
            totalSum += balance[i].toLong()
            if (balance[i] < 0) negIndex = i
        }
        if (negIndex == -1) return 0L
        if (totalSum < 0) return -1L

        var need = -balance[negIndex].toLong()

        // 距离 & 可提供的贡献值
        val sources = ArrayList<Pair<Int, Long>>()

        for (i in 0 until n) {
            if (i == negIndex) continue
            val canAdd = balance[i]
            val d = abs(i - negIndex)
            val dist = minOf(d, n - d)
            sources.add(Pair(dist, canAdd.toLong()))
        }

        sources.sortBy { it.first }

        var cost = 0L
        for ((dist, canAdd) in sources) {
            if (need == 0L) break
            val take = minOf(canAdd, need)
            cost += take * dist
            need -= take
        }

        return if (need == 0L) cost else -1L
    }
}