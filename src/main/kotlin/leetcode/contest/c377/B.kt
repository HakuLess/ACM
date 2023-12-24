package leetcode.contest.c377

import utils.print

fun main() {
    val s = SolutionB()
    s.maximizeSquareArea(4, 3, intArrayOf(2, 3), intArrayOf(2)).print()
}

class SolutionB {
    fun maximizeSquareArea(m: Int, n: Int, hFences: IntArray, vFences: IntArray): Int {
        val mod = 1000000007L
        val hSet = HashSet<Long>()
        val vSet = HashSet<Long>()

        hFences.sort()
        vFences.sort()

        val hList = ArrayList<Int>()
        hList.add(1)
        hList.addAll(hFences.toList())
        hList.add(m)

        val vList = ArrayList<Int>()
        vList.add(1)
        vList.addAll(vFences.toList())
        vList.add(n)

        for (i in hList.indices) {
            for (j in i + 1 until hList.size) {
                hSet.add((hList[j] - hList[i]).toLong())
            }
        }

        for (i in vList.indices) {
            for (j in i + 1 until vList.size) {
                vSet.add((vList[j] - vList[i]).toLong())
            }
        }

//        hSet.joinToString().print()
//        vSet.joinToString().print()

        hSet.sortedDescending().forEach {
            if (it in vSet) {
                return (it * it % mod).toInt()
            }
        }
        return -1
    }
}