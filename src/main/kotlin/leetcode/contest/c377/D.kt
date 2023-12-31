package leetcode.contest.c377

import utils.Graph
import utils.floyd
import utils.print

fun main() {
    val s = SolutionD()

    // 28
    s.minimumCost(
        "abcd",
        "acbe",
        arrayOf("a", "b", "c", "c", "e", "d"),
        arrayOf("b", "c", "b", "e", "b", "e"),
        intArrayOf(2, 5, 5, 1, 2, 20)
    ).print()

//    // 9
//    s.minimumCost(
//        "abcdefgh",
//        "acdeeghh",
//        arrayOf("bcd", "fgh", "thh"),
//        arrayOf("cde", "thh", "ghh"),
//        intArrayOf(1, 3, 5)
//    ).print()
//
//    // -1
//    s.minimumCost(
//        "abcdefgh",
//        "acdeeghh",
//        arrayOf("bcd", "fgh", "thh"),
//        arrayOf("zzz", "zzz", "zzz"),
//        intArrayOf(1, 3, 5)
//    ).print()
}

class SolutionD {
    fun minimumCost(
        source: String,
        target: String,
        original: Array<String>,
        changed: Array<String>,
        cost: IntArray
    ): Long {

        val map = HashMap<Int, Int>()
        var cur = 0
        original.forEach {
            if (it.hashCode() !in map.keys) {
                map[it.hashCode()] = cur
                cur++
            }
        }
        changed.forEach {
            if (it.hashCode() !in map.keys) {
                map[it.hashCode()] = cur
                cur++
            }
        }

        val graph = Graph(map.size)
        for (i in original.indices) {
            val a = map[original[i].hashCode()]!!
            val b = map[changed[i].hashCode()]!!
            if (graph.weight[a]!!.getOrDefault(b, Int.MAX_VALUE) > cost[i]) {
                graph.addEdgeOri(a, b, cost[i])
            }
        }
        val floyd = graph.floyd()
        val dp = LongArray(source.length + 1) { Long.MAX_VALUE / 2 }

        for (i in source.indices) {
            val aCur = StringBuilder()
            aCur.append(source[i])
            val bCur = StringBuilder()
            bCur.append(target[i])
            for (j in i + 1..source.length) {
                val left = aCur.toString().hashCode()
                val right = bCur.toString().hashCode()
                if (j in source.indices) {
                    aCur.append(source[j])
                    bCur.append(target[j])
                }
                if (left == right) {
                    if (i == 0) {
                        dp[j] = 0L
                    } else if (dp[i] != Long.MAX_VALUE / 2) {
                        dp[j] = minOf(dp[j], dp[i])
                    }
                    continue
                }
                val a = map.getOrDefault(left, -1)
                val b = map.getOrDefault(right, -1)
                if (a != -1 && b != -1 && floyd[a][b] < Long.MAX_VALUE / 2) {
                    if (i == 0) {
                        dp[j] = minOf(dp[j], floyd[a][b])
                    } else if (i in dp.indices && dp[i] != Long.MAX_VALUE / 2) {
                        dp[j] = minOf(dp[j], dp[i] + floyd[a][b])
                    }
                }
            }
        }
//        dp.print()
        if (dp.last() == Long.MAX_VALUE / 2) return -1
        return dp.last()
    }
}