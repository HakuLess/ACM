package leetcode.contest.b155

import utils.kmpSearch
import utils.print

fun main() {
    val s = SolutionC()
//    s.countCells(
//        arrayOf(
//            charArrayOf('c', 'a', 'a', 'a'),
//            charArrayOf('a', 'a', 'b', 'a'),
//            charArrayOf('b', 'b', 'a', 'a'),
//            charArrayOf('a', 'a', 'b', 'a'),
//        ), "aba"
//    ).print()

    s.countCells(
        arrayOf(
            charArrayOf('x', 'e'),
            charArrayOf('e', 't'),
            charArrayOf('e', 'c'),
            charArrayOf('y', 'e'),
            charArrayOf('n', 'c'),
        ), "e"
    ).print()
}

class SolutionC {
    fun countCells(grid: Array<CharArray>, pattern: String): Int {
        val n = grid.size
        val m = grid[0].size
        val total = n * m
        val len = pattern.length

        val a = StringBuilder(total)
        for (i in 0 until n) {
            for (j in 0 until m) {
                a.append(grid[i][j])
            }
        }

        val b = StringBuilder(total)
        for (j in 0 until m) {
            for (i in 0 until n) {
                b.append(grid[i][j])
            }
        }

        val aSet = HashSet<Pair<Int, Int>>()
        val bSet = HashSet<Pair<Int, Int>>()

        val aList = ArrayList<Int>()
        kmpSearch(pattern, a.toString()) {
            aList.add(it)
        }
        aList.reversed().forEach {
            repeat(len) { offset ->
                val cur = Pair((it + offset) / m, (it + offset) % m)
                if (cur in aSet) {
                    return@forEach
                } else {
                    aSet.add(cur)
                }
            }
//            println("aaa ${Pair(it / m, it % m)}")
        }

        val bList = ArrayList<Int>()
        kmpSearch(pattern, b.toString()) {
            bList.add(it)
        }

        bList.reversed().forEach {
            repeat(len) { offset ->
                val cur = Pair((it + offset) % n, (it + offset) / n)
                if (cur in bSet) {
                    return@forEach
                } else {
                    bSet.add(cur)
                }
            }
//            println("bbb ${Pair(it % n, it / n)}")
        }

        return aSet.intersect(bSet).size
    }
}